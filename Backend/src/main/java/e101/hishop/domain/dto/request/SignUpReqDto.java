package e101.hishop.domain.dto.request;


import e101.hishop.AppConfig;
import e101.hishop.domain.entity.User;
import e101.hishop.global.enumeration.Gender;
import e101.hishop.global.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor()
@Builder
public class SignUpReqDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotNull
    private Gender gender;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String phone;

    @Email
    private String email;

    @NotBlank
    private String adSelect;

    private Role role;

    public User toUsersEntity(){
        return User.builder()
                .loginId(loginId)
                .gender(gender)
                .birthDate(birthDate)
                .adSelect(adSelect)
                .email(email)
                .name(name)
                .role(role)
                .password(AppConfig.testPasswordEncoder().encode(password))
                .build();
    }
}