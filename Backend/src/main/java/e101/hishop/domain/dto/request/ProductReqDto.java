package e101.hishop.domain.dto.request;

import e101.hishop.AppConfig;
import e101.hishop.domain.entity.Product;
import e101.hishop.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductReqDto {

    @NotBlank
    private String name;

    @NotNull
    private Long price;

    private String rfid;

    private String barcode;

    private String image;

    public Product toProductEntity(){
        return Product.builder()
                .name(name)
                .price(price)
                .rfid(rfid)
                .barcode(barcode)
                .image(image)
                .build();
    }
}
