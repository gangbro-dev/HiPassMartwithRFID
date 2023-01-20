package e101.hishop.domain.dto.request;

import e101.hishop.domain.entity.Cards;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CardSaveReqDto {
    @NotBlank
    private String cardNo;

    private final Boolean isDefault = false;

    @NotBlank
    private String name;

    @NotBlank
    private String validDate;

    public Cards toPaymentEntity(){
        return Cards.builder()
                .cardNo(cardNo)
                .name(name)
                .isDefault(isDefault)
                .validDate(validDate)
                .build();
    }

}
