package flab.just10minutes.product.domain;

import flab.just10minutes.member.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PurchaseActivity {

    private Long purchaseId;
    @NotNull
    private Member member;
    @NotNull
    private Product product;

    private Long amount;
    private LocalDateTime timestamp;

    @Builder
    public PurchaseActivity(Member member, Product product, Long amount) {
        this.member = member;
        this.product = product;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

}
