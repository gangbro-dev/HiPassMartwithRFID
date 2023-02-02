package e101.hishop.service;

import e101.hishop.domain.dto.request.ProductReqDto;
import e101.hishop.domain.dto.response.PayDetailInfoRespDto;
import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.dto.response.ProductRespDto;
import e101.hishop.domain.entity.*;
import e101.hishop.global.common.CommonException;
import e101.hishop.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserJPARepository userJPARepository;
    private final PayJPARepository payJPARepository;
    private final PayDetailJPARepository payDetailJPARepository;
    private final ProductJPARepository productJPARepository;
    private final BranchJPARepository branchJPARepository;

    @Override
    public Long savePay(Pay pays, Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        //TODO setter 삭제하고 의도있게 작성
        pays.setUsersAndPay(user);
        return adminRepository.savePay(pays);
    }

    @Override
    public List<PayInfoRespDto> getPayInfo() {
        return adminRepository.getPayInfo().stream()
                .map(PayInfoRespDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<PayDetailInfoRespDto> getPayDetail(Long buyId) {
        List<PayDetail> payDetails = payDetailJPARepository.findAllByPayId(buyId);
        List<PayDetailInfoRespDto> payDetailList = new ArrayList<>();
        for (PayDetail p: payDetails) {
            payDetailList.add(PayDetailInfoRespDto.of(p));
        }
        return payDetailList;
    }

    @Override
    public List<ProductRespDto> getProduct() {
        List<Product> products = productJPARepository.findAll();
        List<ProductRespDto> productList = new ArrayList<>();
        for (Product p: products) {
            productList.add(ProductRespDto.of(p));
        }
        return productList;
    }

    @Override
    public ProductRespDto getProductDetail(Long productId) {
        Product product = productJPARepository.findById(productId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        return ProductRespDto.of(product);
    }

    public Long editProduct(ProductReqDto dto, Long productId) {
        return productJPARepository.findById(productId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR))
                .updateProduct(dto)
                .getId();
    }

    public void deleteProduct(Long productId) {
        productJPARepository.deleteById(productId);
    }

    @Override
    public Long saveProduct(Product product) {
        return adminRepository.saveProduct(product);
    }

    @Override
    public Long savePayDetail(PayDetail payDetail, Long payId, Long productId, Long branchId) {
        //TODO Exception
        Pay pay = payJPARepository.findById(payId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        Product product = adminRepository.findProductById(productId);
        Branch branch = branchJPARepository.findById(branchId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        payDetail.setPayAndPayDetail(pay);
        payDetail.setProductAndPayDetail(product);
        payDetail.setBranchAndPayDetail(branch);
        return adminRepository.savePayDetail(payDetail);
    }

    @Override
    public Long saveBranch(Branch branch) { return adminRepository.saveBranch(branch); }

    @Override
    public Long saveStaff(Staff staff, Long branchId) {
        //TODO Exception
        Branch branch = branchJPARepository.findById(branchId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        staff.setBranchAndStaff(branch);
        return adminRepository.saveStaff(staff);
    }

    @Override
    public Long saveKiosk(Kiosk kiosk, Long branchId) {
        Branch branch = branchJPARepository.findById(branchId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        kiosk.setBranchAndKiosk(branch);
        return adminRepository.saveKiosk(kiosk);
    }

}