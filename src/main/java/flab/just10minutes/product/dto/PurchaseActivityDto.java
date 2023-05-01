package flab.just10minutes.product.dto;

import flab.just10minutes.member.domain.Member;
import flab.just10minutes.product.domain.Product;


import java.time.LocalDateTime;

public class PurchaseActivityDto {

    private Long purchaseId;
    private Member member;
    private Product product;
    private Long amount;
    private LocalDateTime timestamp;


}
