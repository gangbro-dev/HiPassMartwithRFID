package e101.hishop.domain.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignUpReqDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotBlank
    private String birthDate;

    private String phone;

    @Email
    private String email;

    @NotBlank
    private String adSelect;

}
