package e101.hishop.repository;

import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;

public interface AdminRepository {
    Users findUserById(Long id);
    Payment findPaymentById(Long id);
    Long savePay(Pay pay);
}
