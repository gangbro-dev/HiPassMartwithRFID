package e101.hishop.repository;

import e101.hishop.domain.dto.request.ProductReqDto;
import e101.hishop.domain.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AdminRepositoryImpl implements AdminRepository {

    private final EntityManager em;

    @Override
    public Card findPaymentById(Long id) {
        return em.find(Card.class, id);
    }

    public Boolean editProduct(ProductReqDto dto, Long productId) {
        Product product = em.find(Product.class, productId);
        product.setName(dto.getName());
        return true;
    }

    @Override
    public Long savePay(Pay pays) {
        em.persist(pays);
        return pays.getId();
    }

    @Override
    public List<Pay> getPayInfo() {
        String jpql = "SELECT p FROM Pay p";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public Long saveProduct(Product product) {
        em.persist(product);
        return product.getId();
    }

    @Override
    public Product findProductById(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    public Long savePayDetail(PayDetail payDetail) {
        em.persist(payDetail);
        return payDetail.getId();
    }

    @Override
    public Long saveBranch(Branch branch) {
        em.persist(branch);
        return branch.getId();
    }

    public Long saveStaff(Staff staff) {
        em.persist(staff);
        return staff.getId();
    }

    public Long saveKiosk(Kiosk kiosk) {
        em.persist(kiosk);
        return kiosk.getId();
    }

    @Override
    public Kiosk findKioskById(Long id) {
        return em.find(Kiosk.class, id);
    }


}
