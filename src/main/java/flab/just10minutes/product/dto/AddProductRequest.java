package flab.just10minutes.product.dto;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.domain.SaleStatus;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Getter
public class AddProductRequest {

    private SaleStatus status;
    private String name;
    private String description;
    private Long price;
    private Long totalStock;
    private Long personalLimitAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDealTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDealTime;

    public static Product toProductDomain(AddProductRequest addProduct) {
        return Product.AddProductBuilder()
                .status(addProduct.getStatus())
                .name(addProduct.getName())
                .description(addProduct.getDescription())
                .price(addProduct.getPrice())
                .totalStock(addProduct.getTotalStock())
                .personalLimitAmount(addProduct.personalLimitAmount)
                .startDealTime(addProduct.getStartDealTime())
                .endDealTime(addProduct.getEndDealTime())
                .purchasedStock(0L)
                .build();
    }



}
