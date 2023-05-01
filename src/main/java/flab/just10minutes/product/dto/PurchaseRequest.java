package flab.just10minutes.product.dto;

import lombok.Getter;

@Getter
public class PurchaseRequest {

    private Long productId;

    private Long memberUniqueId;

    private Long amount;

}