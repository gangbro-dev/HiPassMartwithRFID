package e101.hishop.domain.dto.request;


import e101.hishop.global.enumeration.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserInfoReqDto {

    @NotBlank
    //TODO Unique
    private String loginId;
    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private Gender gender;

    private LocalDate birthdate;
    private String phone;

    @Email
    private String email;

    private Long defaultCardId;

    @NotBlank
    private String adSelect;

}
