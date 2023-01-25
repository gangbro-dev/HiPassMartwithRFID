package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = {"user, pays"})
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card {

    //카드이름
    //기본여부
    //카드번호
    //유효기간

    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private Long id;

    private String name;

    private Boolean isDefault;

//추후 암호와 필요
    private String cardNo;

    private String validDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_category_id")
    private CardCategory cardCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Pay> pays = new ArrayList<>();

    //입력하지않으면 기본값 false
//    @PrePersist
//    public void prePersist(){
//        this.isDefault = this.isDefault != null && this.isDefault;
//    }


    public void setUsersAndCards(User user) {
        this.user = user;
        user.getCards().add(this);
    }

    public void setCardCategoryandCards(CardCategory cardCategory) {
        this.cardCategory = cardCategory;
        cardCategory.getCards().add(this);
    }

    @Builder
    public Card(String name, Boolean isDefault, String cardNo, String validDate) {
        this.name = name;
        this.isDefault = isDefault;
        this.cardNo = cardNo;
        this.validDate = validDate;
    }
}

