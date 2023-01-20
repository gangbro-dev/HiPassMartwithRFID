package e101.hishop.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PayInfoReqDto {

    @NotBlank
    private String buy_date;

    @NotNull
    private Long buy_total;
}
