package e101.hishop.repository;

import e101.hishop.domain.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayJPARepository extends JpaRepository<Pay, Long> {
    List<Pay> findAllByUserId(Long userId);
}
