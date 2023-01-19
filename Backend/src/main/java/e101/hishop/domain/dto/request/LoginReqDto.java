package e101.hishop.domain.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Setter
public class LoginReqDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
