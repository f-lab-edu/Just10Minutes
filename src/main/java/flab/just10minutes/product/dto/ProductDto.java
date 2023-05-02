package flab.just10minutes.product.dto;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.domain.SaleStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
