package e101.hishop.repository;

import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.PayDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayDetailJPARepository extends JpaRepository<PayDetail, Long> {
    List<PayDetail> findAllByPayId(Long payId);
}
