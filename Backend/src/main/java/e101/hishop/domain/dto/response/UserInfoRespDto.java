package e101.hishop.domain.dto.response;

import e101.hishop.domain.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class UserInfoRespDto {

    private String userId;

    private String password;

    private String name;

    private String gender;

    private String birthDate;

    private String phone;

    private String email;

    private String adSelect;

    @Builder
    public UserInfoRespDto(String userId, String password, String name, String gender, String birthDate, String phone, String email, String adSelect) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.adSelect = adSelect;
    }
}
