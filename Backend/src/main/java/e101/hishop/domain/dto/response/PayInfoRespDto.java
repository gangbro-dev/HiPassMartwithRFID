package e101.hishop.domain.dto.response;

import e101.hishop.domain.entity.Card;
import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PayInfoRespDto {

    private Long id;

    private User users;

    private Card cards;

    private String buyDate;

    private Long buyTotal;

    @Builder
    public PayInfoRespDto(Long id, User users, Card cards, String buyDate, Long buyTotal) {
        this.id = id;
        this.users = users;
        this.cards = cards;
        this.buyDate = buyDate;
        this.buyTotal = buyTotal;
    }

    public static PayInfoRespDto of(Pay pay) {
        return PayInfoRespDto.builder()
                .id(pay.getId())
                .users(pay.getUser())
                .cards(pay.getCard())
                .buyDate(pay.getBuyDate())
                .buyTotal(pay.getBuyTotal())
                .build();
    }
}
