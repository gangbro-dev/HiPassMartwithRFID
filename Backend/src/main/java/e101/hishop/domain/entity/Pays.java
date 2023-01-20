package e101.hishop.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pays {

    @Id
    @GeneratedValue
    @Column(name = "pay_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Cards cards;

    @NotBlank
    private String buyDate;

    @NotNull
    private Long buyTotal;

    public void setUsersAndPay(Users users) {
        this.users = users;
        users.getPays().add(this);
    }

    public void setPaymentAndPay(Cards cards) {
        this.cards = cards;
        cards.getPays().add(this);
    }

    @Builder
    public Pays(String buyDate, Long buyTotal
    ) {
        this.buyDate = buyDate;
        this.buyTotal = buyTotal;
    }

}
