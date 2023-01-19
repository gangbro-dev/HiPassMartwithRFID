package e101.hishop.repository;

import e101.hishop.domain.dto.request.LoginReqDto;
import e101.hishop.domain.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {

    private final EntityManager em;

    @Override
    public boolean login(LoginReqDto dto) {
        String jpql="select count(u) from Users as u where u.userId = :id and u.password = :password";

        Query query = em.createQuery(jpql);
        query.setParameter("id", dto.getUserId());
        query.setParameter("password", dto.getPassword());

        Long count = (Long) query.getSingleResult();

        return count > 0;
    }

    @Override
    public Long signUp(Users users) {
        em.persist(users);
        return users.getId();
    }
}
