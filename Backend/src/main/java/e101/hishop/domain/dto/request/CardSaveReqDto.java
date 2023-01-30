package e101.hishop.domain.dto.request;

import e101.hishop.domain.entity.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class CardSaveReqDto {
    @NotBlank
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "유효한 카드번호가 아닙니다.")
    //TODO Unique 추가
    private String cardNo;

    @NotBlank
    private String name;

    //TODO LocalDate로 변경 필요
    @NotBlank
    private String validDate;

    public Card toPaymentEntity(){
        return Card.builder()
                .cardNo(cardNo)
                .name(name)
                .validDate(validDate)
                .build();
    }

}
