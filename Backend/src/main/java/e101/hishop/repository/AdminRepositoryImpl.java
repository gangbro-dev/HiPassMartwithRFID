package e101.hishop.repository;

import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AdminRepositoryImpl implements AdminRepository {

    private final EntityManager em;

    @Override
    public Users findUserById(Long id) {
        return em.find(Users.class, id);
    }

    @Override
    public Payment findPaymentById(Long id) {
        return em.find(Payment.class, id);
    }

    @Override
    public Long savePay(Pay pay) {
        em.persist(pay);
        return pay.getId();
    }

}
