package flab.just10minutes.product.dto;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.domain.SaleStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {
    private SaleStatus status;
    private Long productId;
    private String name;
    private String description;
    private Long price;
    private Long totalStock;
    private Long personalLimitAmount;
    private LocalDateTime startDealTime;
    private LocalDateTime endDealTime;
    private Long purchasedStock;

    @Builder
    public ProductDto(Product product) {
        this.status = product.getStatus();
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.totalStock = product.getTotalStock();
        this.personalLimitAmount = product.getPersonalLimitAmount();
        this.startDealTime = product.getStartDealTime();
        this.endDealTime = product.getEndDealTime();
        this.purchasedStock = product.getPurchasedStock();
    }

    public static Product toUpdateDomain(ProductDto productDto) {
        return Product.UpdateProductBuilder()
                .status(productDto.getStatus())
                .productId(productDto.getProductId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .totalStock(productDto.getTotalStock())
                .personalLimitAmount(productDto.getPersonalLimitAmount())
                .startDealTime(productDto.getStartDealTime())
                .endDealTime(productDto.getEndDealTime())
                .purchasedStock(productDto.getPurchasedStock())
                .build();
    }
}
