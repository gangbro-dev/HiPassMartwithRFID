package e101.hishop.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_pk")
    private Long id;

    @NotBlank
    @Column(unique = true)
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

    @OneToMany(mappedBy = "users")
    private List<Payment> payments = new ArrayList<>();

    @Builder
    public Users(String userId, String password, String name, String gender, String birthDate , String email, String phone, String adSelect, List<Payment> payments
    ) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.adSelect = adSelect;
        this.payments = payments;
    }

}
