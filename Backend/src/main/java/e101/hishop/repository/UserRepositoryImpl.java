package e101.hishop.repository;

import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final EntityManager em;

    @Override
    public Users findUserById(Long id) {
        return em.find(Users.class, id);
    }

    @Override
    public Users findUserByLoginId(String userId) {
        return null;
    }

    @Override
    public Users findUserByEmail(String email) {
        return null;
    }

    @Override
    public Long saveCard(Payment payment) {
        em.persist(payment);
        return payment.getId();
    }
}
