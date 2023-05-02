package flab.just10minutes.product.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(exclude = {"purchaseId", "amount", "timestamp"})
public class Purchase {

    private Long purchaseId;
    @NotNull
    private Long memberUniqueId;
    @NotNull
    private Long productId;

    private Long amount;
    private LocalDateTime timestamp;

    @Builder
    public Purchase(Long memberUniqueId, Long productId, Long amount) {
        this.memberUniqueId = memberUniqueId;
        this.productId = productId;
        this.amount = amount;
        this.timestamp = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
    }

}
