package e101.hishop.repository;

import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.Card;

import java.util.List;

public interface AdminRepository {
    Card findPaymentById(Long id);
    Long savePay(Pay pays);
    List<Pay> getPayInfo();
}
