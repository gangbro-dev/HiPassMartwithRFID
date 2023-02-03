package e101.hishop.domain.dto.response;

import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.PayDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PayDetailInfoRespDto {

    private Long id;

    private String productName;

    private String couponName;

    private Integer count;

    private Long price;

    @Builder
    public PayDetailInfoRespDto(Long id, String productName, String couponName, Integer count, Long price) {
        this.id = id;
        this.productName = productName;
        this.couponName = couponName;
        this.count = count;
        this.price = price;
    }

    public static PayDetailInfoRespDto of (PayDetail payDetail) {
        return PayDetailInfoRespDto.builder()
                .id(payDetail.getId())
                .productName(payDetail.getProductName())
                .couponName(payDetail.getCouponName())
                .count(payDetail.getCount())
                .price(payDetail.getPrice())
                .build();
    }
}
