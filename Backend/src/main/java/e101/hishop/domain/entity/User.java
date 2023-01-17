package e101.hishop.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

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

    private String email;

    @NotBlank
    private String ad_select;

}
