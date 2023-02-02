package e101.hishop.service;

import e101.hishop.domain.dto.request.ProductReqDto;
import e101.hishop.domain.dto.response.PayDetailInfoRespDto;
import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.dto.response.ProductRespDto;
import e101.hishop.domain.entity.*;

import java.util.List;

public interface AdminService {
    Pay savePay(Pay pays, Long userId);

    List<PayInfoRespDto> getPayInfo();
    List<PayDetailInfoRespDto> getPayDetail(Long buyId);
    List<ProductRespDto> getProduct();
    ProductRespDto getProductDetail(Long productId);
    Long editProduct(ProductReqDto dto, Long productId);

    void deleteProduct(Long productId);
    Product saveProduct(Product product);

    PayDetail savePayDetail(PayDetail payDetail, Long payId, Long productId, Long branchId);

    Branch saveBranch(Branch branch);

    Staff saveStaff(Staff staff, Long branchId);

    Kiosk saveKiosk(Kiosk kiosk, Long branchId);

}
