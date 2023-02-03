package e101.hishop.domain.dto.response;

import e101.hishop.domain.entity.Pay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PayInfoRespDto {

    private Long id;

    private String userName;

    private String cardName;

    private String buyDate;

    private Long buyTotal;

    @Builder
    public PayInfoRespDto(Long id, String userName, String cardName, String buyDate, Long buyTotal) {
        this.id = id;
        this.userName = userName;
        this.cardName = cardName;
        this.buyDate = buyDate;
        this.buyTotal = buyTotal;
    }

    public static PayInfoRespDto of(Pay pay) {
        return PayInfoRespDto.builder()
                .id(pay.getId())
                .userName(pay.getUserName())
                .cardName(pay.getCardName())
                .buyDate(pay.getBuyDate())
                .buyTotal(pay.getBuyTotal())
                .build();
    }
}
