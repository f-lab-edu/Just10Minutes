package flab.just10minutes.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PurchaseHistory {
    private List<Purchase> purchases;

    @Builder
    public PurchaseHistory(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Builder
    public PurchaseHistory(Purchase... purchases) {
        this.purchases = new ArrayList<>(Arrays.asList(purchases));
    }

    public void addHistory(Purchase... purchases) {
        for(Purchase purchase : purchases) {
            this.purchases.add(purchase);
        }
    }

    public Long getActivitiesCount() {
        return this.purchases.stream().count();
    }

}

