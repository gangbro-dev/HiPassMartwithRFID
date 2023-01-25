package e101.hishop.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pay {

    @Id
    @GeneratedValue
    @Column(name = "pay_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @NotBlank
    private String buyDate;

    @NotNull
    private Long buyTotal;

    public void setUsersAndPay(User user) {
        this.user = user;
        user.getPays().add(this);
    }

    public void setPaymentAndPay(Card card) {
        this.card = card;
        card.getPays().add(this);
    }

    @Builder
    public Pay(String buyDate, Long buyTotal) {
        this.buyDate = buyDate;
        this.buyTotal = buyTotal;
    }
}
