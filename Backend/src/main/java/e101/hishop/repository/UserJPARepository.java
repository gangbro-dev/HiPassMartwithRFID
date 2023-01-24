package e101.hishop.repository;

import e101.hishop.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    Boolean existsByLoginId(String loginId);
}