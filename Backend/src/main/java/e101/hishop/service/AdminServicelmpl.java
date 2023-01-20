package e101.hishop.service;

import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;
import e101.hishop.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServicelmpl {

    private final AdminRepository adminRepository;

    @Override
    public Long savePay(Pay pay, Long userId, Long paymentId) {
        Users users = adminRepository.findUserById(userId);
        Payment payment = adminRepository.findPaymentById(paymentId);
        log.info("users, {}", users);
        pay.setUsersAndPay(users);
        pay.setPaymentAndPay(payment);
        return adminRepository.savePay(pay);
    }
}
