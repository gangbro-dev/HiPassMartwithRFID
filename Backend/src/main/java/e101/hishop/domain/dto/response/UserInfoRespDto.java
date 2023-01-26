package e101.hishop.domain.dto.response;

import e101.hishop.domain.entity.User;
import e101.hishop.global.enumeration.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserInfoRespDto {
    private String userId;

    private String password;

    private String name;

    private Gender gender;

    private LocalDate birthDate;

    private String phone;

    private String email;

    private String adSelect;

    @Builder
    public UserInfoRespDto(String userId, String password, String name, Gender gender, LocalDate birthDate, String phone, String email, String adSelect) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.adSelect = adSelect;
    }

    //TODO 유저정보 받아올때 password 뺄것인지 생각
    public static UserInfoRespDto of(User user) {
        return UserInfoRespDto.builder()
                .userId(user.getLoginId())
                .email(user.getEmail())
                .name(user.getName())
                .gender(user.getGender())
                .password(user.getPassword())
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .adSelect(user.getAdSelect())
                .build();
    }
}
