package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Branch {

    @Id
    @GeneratedValue
    @Column(name = "branch_id")
    private Long id;

    @NotBlank
    private String branchName;

    @NotBlank
    private String region;

    @JsonIgnore
    @OneToMany(mappedBy = "branch", cascade = CascadeType.REMOVE)
    private List<Staff> staff = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "branch", cascade = CascadeType.REMOVE)
    private List<Kiosk> kiosks = new ArrayList<>();
    //지점장 아이디 어떻게 하지
}
