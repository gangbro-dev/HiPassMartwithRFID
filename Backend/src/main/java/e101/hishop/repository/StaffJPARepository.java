package e101.hishop.repository;

import e101.hishop.domain.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffJPARepository extends JpaRepository<Staff, Long> {
}
