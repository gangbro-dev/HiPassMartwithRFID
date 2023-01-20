package e101.hishop.service;

import e101.hishop.domain.entity.Pay;

public interface AdminService {
    Long savePay(Pay pay, Long userId, Long paymentId);


}
