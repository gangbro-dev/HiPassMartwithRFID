package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shopping {
    @Id
    @GeneratedValue
    @Column(name = "shopping_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kiosk_id")
    @JsonIgnore
    private Kiosk kiosk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
    private Integer count;
    private Long price;

    public void setKioskAndShopping(Kiosk kiosk) {
        this.kiosk = kiosk;
        kiosk.getShopping().add(this);
    }

    public void setProductAndShopping(Product product) {
        this.product = product;
        product.getShoppings().add(this);
    }

    @Builder
    public Shopping(Long id, Integer count, Long price) {
        this.id = id;
        this.count = count;
        this.price = price;
    }
}
