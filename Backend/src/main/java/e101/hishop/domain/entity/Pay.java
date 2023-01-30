package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;

    @NotBlank
    private String userName;

    @NotBlank
    private String cardName;

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
    public Pay(String userName, String cardName, String buyDate, Long buyTotal) {
        this.userName = userName;
        this.cardName = cardName;
        this.buyDate = buyDate;
        this.buyTotal = buyTotal;
    }
}
