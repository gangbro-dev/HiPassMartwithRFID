package e101.hishop.repository;

import e101.hishop.domain.entity.User;

public interface UserRepository {
    Long saveUser(User user);

    User findUserById(Long id);

    User findUserByLoginId(String userId);

    User findUserByEmail(String email);
}
