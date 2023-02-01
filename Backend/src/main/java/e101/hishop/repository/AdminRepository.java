package e101.hishop.repository;

import e101.hishop.domain.entity.*;

import java.util.List;

public interface AdminRepository {
    Card findPaymentById(Long id);
    Long savePay(Pay pays);
    List<Pay> getPayInfo();
    Long saveProduct(Product product);
    Product findProductById(Long id);
    Long savePayDetail(PayDetail payDetail);
    Long saveBranch(Branch branch);
    Long saveStaff(Staff staff);
    Long saveKiosk(Kiosk kiosk);
    Kiosk findKioskById(Long id);

}
