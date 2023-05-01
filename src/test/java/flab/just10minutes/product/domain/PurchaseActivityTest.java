package flab.just10minutes.product.domain;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

@Slf4j
class PurchaseActivityTest {

    @Test
    @DisplayName("ArrayList test")
    void test() {
        List<PurchaseActivity> activityList = new ArrayList<>();
        for (int i = 1; i < 1000000; i++) {
            activityList.add(PurchaseActivity.builder()
                    .memberUniqueId(Long.parseLong(String.valueOf(i)))
                    .productId(1L)
                    .amount(1L)
                    .build());
        }
        Random r = new Random();
        Long randomMemberId = r.nextLong(1000000);

        PurchaseActivity newAct = PurchaseActivity.builder()
                .memberUniqueId(randomMemberId)
                .productId(1L)
                .amount(1L)
                .build();
        Long st = System.currentTimeMillis();
        log.info("start searching");


        Boolean result = activityList.contains(newAct);
        log.info("id : {}", randomMemberId);
        log.info("is exist : {}", result);
        Long ed = System.currentTimeMillis();
        log.info("searching time : {}", ed - st );
    }

    @Test
    @DisplayName("HashMap test")
    void test2() {
        Map<PurchaseActivity, Long> activityMap = new HashMap<>();
        for (int i = 1; i < 1000000; i++) {
            activityMap.put(PurchaseActivity.builder()
                    .memberUniqueId(Long.parseLong(String.valueOf(i)))
                    .productId(1L)
                    .amount(1L)
                    .build(), Long.parseLong(String.valueOf(i)));
        }
        Random r = new Random();
        Long randomMemberId = r.nextLong(1000000);

        PurchaseActivity newAct = PurchaseActivity.builder()
                .memberUniqueId(randomMemberId)
                .productId(1L)
                .amount(1L)
                .build();

        Long st = System.currentTimeMillis();
        log.info("start searching");
        Boolean result = activityMap.containsKey(newAct);
        log.info("id : {}", randomMemberId);
        log.error("is exist : {}", result);
        Long ed = System.currentTimeMillis();
        log.info("searching time : {}", ed - st );
    }
}