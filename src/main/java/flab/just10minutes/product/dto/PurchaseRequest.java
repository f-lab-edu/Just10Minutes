package flab.just10minutes.product.dto;

import lombok.Getter;

@Getter
public class PurchaseRequest {

    private Long ProductId;

    private Long MemberOpenId;

    private Long amount;

}