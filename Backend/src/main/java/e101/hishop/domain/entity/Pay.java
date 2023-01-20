package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "customer_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @NotBlank
    private String buy_date;

    @NotBlank
    private Integer buy_total;

    public void setUsersAndPay(Users users) {
        this.users = users;
        users.getPay().add(this);
    }

    public void setPaymentAndPay(Payment payment) {
        this.payment = payment;
        payment.getPay().add(this);
    }

    @Builder
    public Pay(String buy_date, Integer buy_total
    ) {
        this.buy_date = buy_date;
        this.buy_total = buy_total;
    }

}
