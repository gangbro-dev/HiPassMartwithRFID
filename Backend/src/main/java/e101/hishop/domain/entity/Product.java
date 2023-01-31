package e101.hishop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long price;

    private String rfid;

    private String barcode;

    private String image;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<PayDetail> payDetails = new ArrayList<>();


    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<Shopping> shoppings = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "manu_id")
//    private Manufacturer manufacturer;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private ProductCategory productCategory;
}
