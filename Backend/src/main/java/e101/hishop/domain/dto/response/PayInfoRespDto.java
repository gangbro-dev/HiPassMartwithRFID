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

    private String buy_date;

    private Long buy_total;

    @Builder
    public PayInfoRespDto(Long id, Users users, Cards cards, String buy_date, Long buy_total) {
        this.id = id;
        this.users = users;
        this.cards = cards;
        this.buy_date = buy_date;
        this.buy_total = buy_total;
    }
}
