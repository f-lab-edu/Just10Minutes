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
    private List<PurchaseActivity> activities;

    @Builder
    public PurchaseHistory(List<PurchaseActivity> activities) {
        this.activities = activities;
    }

    @Builder
    public PurchaseHistory(PurchaseActivity... activities) {
        this.activities = new ArrayList<>(Arrays.asList(activities));
    }

    public void addHistory(PurchaseActivity... activities) {
        for(PurchaseActivity activity : activities) {
            this.activities.add(activity);
        }
    }

    public Long getActivitiesCount() {
        return this.activities.stream().count();
    }

    public Boolean isAlreadyPurchased(PurchaseActivity activity) {
        return this.activities.contains(activity);
    }
}

