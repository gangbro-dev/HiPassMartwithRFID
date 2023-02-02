package e101.hishop.repository;

import e101.hishop.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardJPARepository extends JpaRepository<Card, Long> {

    List<Card> findAllByUserId(Long id);
}
