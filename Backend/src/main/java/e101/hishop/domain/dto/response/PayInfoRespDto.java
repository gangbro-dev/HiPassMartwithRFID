package e101.hishop.domain.dto.response;

import e101.hishop.domain.entity.Pay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PayInfoRespDto {

    private Long id;

    private Long userId;

    private Long cardId;

    private String buyDate;

    private Long buyTotal;

    @Builder
    public PayInfoRespDto(Long id, Long userId, Long cardId, String buyDate, Long buyTotal) {
        this.id = id;
        this.userId = userId;
        this.cardId = cardId;
        this.buyDate = buyDate;
        this.buyTotal = buyTotal;
    }

    public static PayInfoRespDto of(Pay pay) {
        return PayInfoRespDto.builder()
                .id(pay.getId())
                .userId(pay.getUser().getId())
                .cardId(pay.getCard().getId())
                .buyDate(pay.getBuyDate())
                .buyTotal(pay.getBuyTotal())
                .build();
    }
}
