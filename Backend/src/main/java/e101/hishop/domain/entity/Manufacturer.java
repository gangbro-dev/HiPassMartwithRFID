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
public class Manufacturer {

    @Id
    @GeneratedValue
    @Column(name = "manu_id")
    private Long id;

    @NotBlank
    private String name;

    private String address;

    private String tel;

//    @JsonIgnore
//    @Builder.Default
//    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.REMOVE)
//    private List<Product> products = new ArrayList<>();


}
