package e101.hishop.domain.dto.request;


import e101.hishop.domain.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Users toUsersEntity(){
        return Users.builder()
                .userId(userId)
                .gender(gender)
                .birthDate(birthDate)
                .adSelect(adSelect)
                .email(email)
                .name(name)
                .password(password)
                .build();
    }

}
