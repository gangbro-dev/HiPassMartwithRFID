package e101.hishop.repository;

import e101.hishop.domain.dto.request.EditNameReqDto;
import e101.hishop.domain.entity.Card;
import e101.hishop.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager em;

    @Override
    public Long saveCard(Card card) {
        em.persist(card);
        return card.getId();
    }

    @Override
    public Boolean deleteCard(Long cardId) {
        Card card = em.find(Card.class, cardId);
        em.remove(card);
        return true;
    }

    @Override
    public List<Card> getCardInfo(Long userId) {
        String jpql = "SELECT p FROM Card p WHERE p.user.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    User getUserInfo(Long userId) {
        return em.find(User.class, userId);
    }

    @Override
    public Boolean editName(Long cardId, EditNameReqDto dto) {
        Card card = em.find(Card.class, cardId);
        card.setName(dto.getName());
        return true;
    }
}
