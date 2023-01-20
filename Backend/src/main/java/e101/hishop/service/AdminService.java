package e101.hishop.service;

import e101.hishop.domain.entity.Payment;

public interface AdminService {
    Long savePay(Payment payment, Long userId, Long paymentId);


}
