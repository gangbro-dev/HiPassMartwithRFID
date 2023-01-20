package e101.hishop.repository;

import e101.hishop.domain.entity.Pays;
import e101.hishop.domain.entity.Cards;
import e101.hishop.domain.entity.Users;

import java.util.List;

public interface AdminRepository {
    Users findUserById(Long id);
    Cards findPaymentById(Long id);
    Long savePay(Pays pays);
    List<Pays> getPayInfo();
}
