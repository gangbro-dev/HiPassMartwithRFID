package e101.hishop.repository;

import e101.hishop.domain.entity.Pays;
import e101.hishop.domain.entity.Cards;
import e101.hishop.domain.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AdminRepositoryImpl implements AdminRepository {

    private final EntityManager em;

    @Override
    public Users findUserById(Long id) {
        return em.find(Users.class, id);
    }

    @Override
    public Cards findPaymentById(Long id) {
        return em.find(Cards.class, id);
    }

    @Override
    public Long savePay(Pays pays) {
        em.persist(pays);
        return pays.getId();
    }

    @Override
    public List<Pays> getPayInfo() {
        String jpql = "SELECT p FROM Pays p";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }


}
