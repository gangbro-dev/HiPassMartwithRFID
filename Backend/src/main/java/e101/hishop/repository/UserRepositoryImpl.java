package e101.hishop.repository;

import e101.hishop.domain.dto.request.EditNameReqDto;
import e101.hishop.domain.entity.Cards;
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
    public Long saveCard(Cards cards) {
        em.persist(cards);
        return cards.getId();
    }

    @Override
    public Boolean deleteCard(Long cardId) {
        Cards cards = em.find(Cards.class, cardId);
        em.remove(cards);
        return true;
    }

    @Override
    public List<Cards> getCardInfo(Long userId) {
        String jpql = "SELECT p FROM Cards p WHERE p.users.id = :id";
        Query query = em.createQuery(jpql);
        query.setParameter("id",userId );
        return query.getResultList();
    }

    @Override
    public Users getUserInfo(Long userPK) {
        return em.find(Users.class, userPK);
    }

    @Override
    public Boolean editName(Long cardId, EditNameReqDto dto) {
        Cards cards = em.find(Cards.class, cardId);
        cards.setName(dto.getName());
        return true;
    }
}
