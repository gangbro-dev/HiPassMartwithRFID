package e101.hishop.service;

import e101.hishop.domain.dto.request.ProductReqDto;
import e101.hishop.domain.dto.request.StaffReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.*;
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
    private final UserJPARepository userJPARepository;
    private final PayJPARepository payJPARepository;
    private final PayDetailJPARepository payDetailJPARepository;
    private final StaffJPARepository staffJPARepository;
    private final KioskJPARepository kioskJPARepository;
    private final ProductJPARepository productJPARepository;
    private final BranchJPARepository branchJPARepository;
    private final StaffJPARepository staffJPARepository;

    @Override
    public Pay savePay(Pay pays, Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "Pays객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        //TODO setter 삭제하고 의도있게 작성
        pays.setUsersAndPay(user);
        return payJPARepository.save(pays);
    }

    @Override
    public List<PayInfoRespDto> getPayInfo() {
        return payJPARepository.findAll().stream()
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
                .orElseThrow(() -> new CommonException(2, "Product객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        return ProductRespDto.of(product);
    }

    public Long editProduct(ProductReqDto dto, Long productId) {
        return productJPARepository.findById(productId)
                .orElseThrow(() -> new CommonException(2, "Product객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR))
                .updateProduct(dto)
                .getId();
    }

    public void deleteProduct(Long productId) {
        productJPARepository.deleteById(productId);
    }

    @Override
    public Product saveProduct(Product product) {
        return productJPARepository.save(product);
    }

    public List<UserInfoRespDto> getUsers() {
        List<User> users = userJPARepository.findAll();
        List<UserInfoRespDto> userList = new ArrayList<>();
        for (User p: users) {
            userList.add(UserInfoRespDto.of(p));
        }
        return userList;
    }

    @Override
    public UserInfoRespDto getUser(Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        return UserInfoRespDto.of(user);
    }

    @Override
    public Long modifyUser(UserInfoReqDto dto, Long userId) {
        return userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR))
                .updateUserInfo(dto)
                .updateUserByAdmin(dto)
                .getId();
    }

    public void deleteUser(Long userId) {
        userJPARepository.deleteById(userId);
    }
    @Override
    public List<StaffRespDto> getStaff() {
        List<Staff> staff = staffJPARepository.findAll();
        List<StaffRespDto> staffList = new ArrayList<>();
        for (Staff p: staff) {
            staffList.add(StaffRespDto.of(p));
        }
        return staffList;
    }
    @Override
    public StaffRespDto getStaffDetail(Long employeeId) {
        Staff staff = staffJPARepository.findById(employeeId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        return StaffRespDto.of(staff);
    }
    @Override
    public Long modifyStaff(StaffReqDto dto, Long employeeId) {
        return staffJPARepository.findById(employeeId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR))
                .updateStaff(dto)
                .getId();
    }

    public void deleteStaff(Long employeeId) {
        staffJPARepository.deleteById(employeeId);
    }

    @Override
    public PayDetail savePayDetail(PayDetail payDetail, Long payId, Long productId, Long branchId) {
        //TODO Exception
        Pay pay = payJPARepository.findById(payId)
                .orElseThrow(() -> new CommonException(2, "Pay객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        Product product = productJPARepository.findById(productId)
                .orElseThrow(() -> new CommonException(2, "Product객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));;
        Branch branch = branchJPARepository.findById(branchId)
                .orElseThrow(() -> new CommonException(2, "Branch객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        payDetail.setPayAndPayDetail(pay);
        payDetail.setProductAndPayDetail(product);
        payDetail.setBranchAndPayDetail(branch);
        return payDetailJPARepository.save(payDetail);
    }

    @Override
    public Branch saveBranch(Branch branch) { return branchJPARepository.save(branch); }

    @Override
    public Staff saveStaff(Staff staff, Long branchId) {
        //TODO Exception
        Branch branch = branchJPARepository.findById(branchId)
                .orElseThrow(() -> new CommonException(2, "Branch객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        staff.setBranchAndStaff(branch);
        return staffJPARepository.save(staff);
    }

    @Override
    public Kiosk saveKiosk(Kiosk kiosk, Long branchId) {
        Branch branch = branchJPARepository.findById(branchId)
                .orElseThrow(() -> new CommonException(2, "Branch객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        kiosk.setBranchAndKiosk(branch);
        return kioskJPARepository.save(kiosk);
    }

}