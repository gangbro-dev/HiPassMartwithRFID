package e101.hishop.domain.dto.request;


import e101.hishop.global.enumeration.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserInfoReqDto {

    @NotBlank
    private String password;

    @NotBlank
    private Gender gender;

    private String phone;

    @Email
    private String email;

    @NotBlank
    private String adSelect;

}
