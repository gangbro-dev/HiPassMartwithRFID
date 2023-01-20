package e101.hishop.domain.dto.response;

import e101.hishop.domain.entity.Cards;
import e101.hishop.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PayInfoRespDto {

    private Long id;

    private Users users;

    private Cards cards;

    private String buyDate;

    private Long buyTotal;

    @Builder
    public PayInfoRespDto(Long id, Users users, Cards cards, String buyDate, Long buyTotal) {
        this.id = id;
        this.users = users;
        this.cards = cards;
        this.buyDate = buyDate;
        this.buyTotal = buyTotal;
    }
}
