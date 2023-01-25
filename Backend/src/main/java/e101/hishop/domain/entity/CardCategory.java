package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@ToString(exclude = {"user, pays"})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardCategory {

    @Id
    @GeneratedValue
    @Column(name = "card_category_id")
    private Long id;

    private String classification;

    private String paymentName;

    private String img;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "cardCategory", cascade = CascadeType.REMOVE)
    private List<Card> cards = new ArrayList<>();

}

