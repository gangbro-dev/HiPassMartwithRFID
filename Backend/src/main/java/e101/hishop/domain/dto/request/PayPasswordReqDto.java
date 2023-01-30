package e101.hishop.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Setter
public class PayPasswordReqDto {
    @NotBlank
    @Size(min = 4, max = 4)
    @Pattern(regexp="[0-9]{4}", message="비밀번호는 숫자 4자리여야 합니다.")
    private String payPassword;
}
