package e101.hishop.domain.dto.request;

import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;
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

    public Payment toPaymentEntity(){
        return Payment.builder()
                .cardNo(cardNo)
                .name(name)
                .isDefault(isDefault)
                .validDate(validDate)
                .build();
    }

}
