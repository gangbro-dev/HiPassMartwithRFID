package e101.hishop.service;

import e101.hishop.domain.dto.request.ProductReqDto;
import e101.hishop.domain.dto.response.PayDetailInfoRespDto;
import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.dto.response.ProductRespDto;
import e101.hishop.domain.entity.*;

import java.util.List;

public interface AdminService {
    Long savePay(Pay pays, Long userId);

    List<PayInfoRespDto> getPayInfo();
    List<PayDetailInfoRespDto> getPayDetail(Long buyId);
    List<ProductRespDto> getProduct();
    ProductRespDto getProductDetail(Long productId);
    Long editProduct(ProductReqDto dto, Long productId);

    void deleteProduct(Long productId);
    Long saveProduct(Product product);

    Long savePayDetail(PayDetail payDetail, Long payId, Long productId, Long branchId);

    Long saveBranch(Branch branch);

    Long saveStaff(Staff staff, Long branchId);

    Long saveKiosk(Kiosk kiosk, Long branchId);

}
