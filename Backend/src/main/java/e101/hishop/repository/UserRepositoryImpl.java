package e101.hishop.repository;

import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    @Override
    public List<Payment> getCardInfo(Long userId) {
        String jpql = "SELECT p FROM Payment p WHERE p.users.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id",userId );
        return query.getResultList();
    }

    @Override
    public Users getUserInfo(Long userPK) {
        return em.find(Users.class, userPK);
    }
}
