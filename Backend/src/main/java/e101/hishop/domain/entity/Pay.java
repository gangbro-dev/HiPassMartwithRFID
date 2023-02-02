package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Pay {

    @Id
    @GeneratedValue
    @Column(name = "pay_id")
    private Long id;

    //TODO LAZY로 변경?
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

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


    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "pay")
    private List<PayDetail> payDetails = new ArrayList<>();

    @Builder
    public Pay(String userName, String cardName, String buyDate, Long buyTotal) {
        this.userName = userName;
        this.cardName = cardName;
        this.buyDate = buyDate;
        this.buyTotal = buyTotal;
    }
}
