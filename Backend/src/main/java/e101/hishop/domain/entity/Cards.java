package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cards {

    //카드이름
    //기본여부
    //카드번호
    //유효기간

    @Id
    @GeneratedValue
    @Column(name = "pay_id")
    private Long id;

    private String name;

    private Boolean isDefault;

//추후 암호와 필요
    private String cardNo;

    private String validDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_pk")
    private Users users;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    private List<Pays> pays = new ArrayList<>();

    //입력하지않으면 기본값 false
//    @PrePersist
//    public void prePersist(){
//        this.isDefault = this.isDefault != null && this.isDefault;
//    }


    public void setUsersAndPayments(Users users) {
        this.users = users;
        users.getPayments().add(this);
    }

    @Builder
    public Cards(String name, Boolean isDefault, String cardNo, String validDate) {
        this.name = name;
        this.isDefault = isDefault;
        this.cardNo = cardNo;
        this.validDate = validDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDefault=" + isDefault +
                ", cardNo='" + cardNo + '\'' +
                ", validDate='" + validDate + '\'' +
                ", users=" + users +
                '}';
    }
}

