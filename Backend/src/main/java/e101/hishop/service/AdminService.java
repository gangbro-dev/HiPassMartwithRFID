package e101.hishop.service;

import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.entity.*;

import java.util.List;

public interface AdminService {
    Long savePay(Pay pays, Long userId);

    List<PayInfoRespDto> getPayInfo();

    Long saveProduct(Product product);

    Long savePayDetail(PayDetail payDetail, Long payId, Long productId, Long branchId);

    Long saveBranch(Branch branch);

    Long saveStaff(Staff staff, Long branchId);

    Long saveKiosk(Kiosk kiosk, Long branchId);

}
