package flab.just10minutes.product.dto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PurchaseDto {

    private Long purchaseId;
    private String productName;
    private Long price;
    private Long amount;
    private String memberId;
    private LocalDateTime timestamp;


}
