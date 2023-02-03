package e101.hishop.repository;

import e101.hishop.domain.entity.Kiosk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KioskJPARepository extends JpaRepository<Kiosk, Long> {
}
