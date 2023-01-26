package e101.hishop.domain.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CardInfoRespDto {

    private Long cardId;

    @NotBlank
    private String cardNo;

    private Boolean isDefault;

    @NotBlank
    private String name;

    @NotBlank
    private String validDate;

    @Builder
    public CardInfoRespDto(Long cardId, String cardNo, String name, String validDate, Boolean isDefault) {
        this.cardId = cardId;
        this.cardNo = cardNo;
        this.name = name;
        this.validDate = validDate;
        this.isDefault = isDefault;
    }
}
