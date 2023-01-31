package e101.hishop.domain.dto.request;


import e101.hishop.AppConfig;
import e101.hishop.domain.entity.User;
import e101.hishop.global.enumeration.Gender;
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
@AllArgsConstructor
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


    public User toUsersEntity(){
        return User.builder()
                .loginId(loginId)
                .gender(gender)
                .birthDate(birthDate)
                .adSelect(adSelect)
                .email(email)
                .name(name)
                .password(AppConfig.testPasswordEncoder().encode(password))
                .build();
    }
//    SignUpReqDto.builder()
//            .loginId("user1234!")
//                .gender(Gender.MALE)
//                .birthDate(LocalDate.of(1993,12,31))
//            .adSelect("YES")
//                .email("EMAIL@naver.com")
//                .name("NAME")
//                .role(Role.ROLE_USER)
//                .password("user1234!")
//                .build().toUsersEntity());

//    {
//        "loginId": "user123456",
//            "password" :"pass1234!!",
//            "name" :"NAMMME",
//            "gender" :"MALE",
//            "birthDate" :"1919-11-01",
//            "adSelect": "YES"
//    }
}