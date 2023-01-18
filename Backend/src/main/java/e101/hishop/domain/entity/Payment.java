package e101.hishop.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue
    @Column(name = "pay_id")
    private Long id;

    private String name;

    private boolean isDefault;

    private Long cardNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private Users users;

    //
//    public void changeUsers(Users users) {
//        this.users = users;
//        users.getPayments().add(this);
//    }


}
