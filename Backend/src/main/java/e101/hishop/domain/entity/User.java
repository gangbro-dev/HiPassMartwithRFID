package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import e101.hishop.domain.dto.request.PayPasswordReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.global.enumeration.Gender;
import e101.hishop.global.enumeration.Role;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@ToString(exclude = {"cards", "pays"})
@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;

    private String phone;
    private String email;

//    private String payPassword;

    private String adSelect;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Card> cards = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Pay> pays = new ArrayList<>();

    public User updateUserInfo(UserInfoReqDto dto) {
        password = StringUtils.hasText(dto.getPassword()) ? dto.getPassword() : password;
        gender = dto.getGender() != null ? dto.getGender() : gender;
        phone = StringUtils.hasText(dto.getPhone()) ? dto.getPhone() : phone;
        email = StringUtils.hasText(dto.getEmail()) ? dto.getEmail() : email;
        adSelect = StringUtils.hasText(dto.getAdSelect()) ? dto.getAdSelect() : adSelect;
        return this;
    }

//    public void changePayPassword(PayPasswordReqDto dto) {
//        this.payPassword = dto.getPayPassword();
//    }
}
