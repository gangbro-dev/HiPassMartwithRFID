package e101.hishop.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_primary_id")
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String userId;

    @NotBlank
    private String pwd;

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
    private String ad_select;

    @Builder
    public Users(String userId, String pwd, String name, String gender, String birthDate , String email, String phone, String ad_select) {
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.ad_select = ad_select;
    }

}
