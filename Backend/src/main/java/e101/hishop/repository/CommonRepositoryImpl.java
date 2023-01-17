package e101.hishop.repository;

import e101.hishop.domain.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CommonRepositoryImpl implements CommonRepository{

    private final EntityManager em;

    @Override
    public Long signUp(Users users) {
        em.persist(users);
        return users.getId();
    }
}
