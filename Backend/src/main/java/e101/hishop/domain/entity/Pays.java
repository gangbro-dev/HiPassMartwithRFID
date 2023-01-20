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
    private String buy_date;

    @NotNull
    private Long buy_total;

    public void setUsersAndPay(Users users) {
        this.users = users;
        users.getPay().add(this);
    }

    public void setPaymentAndPay(Cards cards) {
        this.cards = cards;
        cards.getPay().add(this);
    }

    @Builder
    public Pays(String buy_date, Long buy_total
    ) {
        this.buy_date = buy_date;
        this.buy_total = buy_total;
    }

}
