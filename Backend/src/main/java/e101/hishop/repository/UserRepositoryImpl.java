package e101.hishop.repository;

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
        return null;
    }

    @Override
    public Users findUserByLoginId(String userId) {
        return null;
    }

    @Override
    public Users findUserByEmail(String email) {
        return null;
    }
}
