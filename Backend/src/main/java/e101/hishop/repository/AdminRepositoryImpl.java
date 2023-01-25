package e101.hishop.repository;

import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.Card;
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
    public Card findPaymentById(Long id) {
        return em.find(Card.class, id);
    }

    @Override
    public Long savePay(Pay pays) {
        em.persist(pays);
        return pays.getId();
    }

    @Override
    public List<Pay> getPayInfo() {
        String jpql = "SELECT p FROM Pay p";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
}
