package flab.just10minutes.product.domain;

import flab.just10minutes.member.domain.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    private SaleStatus status;
    private Long productId;
    private String name;
    private String description;
    private Long price;
    private Long totalStock;
    private Long personalLimitAmount;
    private LocalDateTime startDealTime;
    private LocalDateTime endDealTime;
    private Long purchasedStock;
    private PurchaseHistory purchaseHistory;

    @Builder(builderClassName="AddProductBuilder", builderMethodName="AddProductBuilder")
    public Product(SaleStatus status, String name, String description, Long price, Long totalStock, Long personalLimitAmount, LocalDateTime startDealTime, LocalDateTime endDealTime, Long purchasedStock) {
        this.status = status;
        this.name = name;
        this.description = description;
        this.price = price;
        this.totalStock = totalStock;
        this.personalLimitAmount = personalLimitAmount;
        this.startDealTime = startDealTime;
        this.endDealTime = endDealTime;
        this.purchasedStock = purchasedStock;
    }

    @Builder(builderClassName = "UpdateProductBuilder", builderMethodName = "UpdateProductBuilder")
    public Product(Long productId, SaleStatus status, String name, String description, Long price, Long totalStock, Long personalLimitAmount, LocalDateTime startDealTime, LocalDateTime endDealTime, Long purchasedStock) {
        this.productId = productId;
        this.status = status;
        this.name = name;
        this.description = description;
        this.price = price;
        this.totalStock = totalStock;
        this.personalLimitAmount = personalLimitAmount;
        this.startDealTime = startDealTime;
        this.endDealTime = endDealTime;
        this.purchasedStock = purchasedStock;
    }

    public Boolean isPersonalLimitOver(Long amount) {
        return compareAB(amount, this.getPersonalLimitAmount()) ==1 ? true : false;
    }

    public Boolean isTotalStockOver() {
        return compareAB(this.purchasedStock, this.totalStock) == 1 ? true : false;
    }

    private Boolean isTotalStockOver(Long purchasedStock) {
        return compareAB(purchasedStock, this.totalStock) == -1 ? false : true;
    }

    public Boolean isBalanceOver(Member member) {
        return compareAB(this.price, member.getBalance()) == 1 ? true : false;
    }

    private int compareAB(Long A, Long B) {
        return A > B ? 1 : (A == B ? 0 : -1);
    }

    public Boolean isDealTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        if (this.startDealTime.isAfter(currentTime) && this.endDealTime.isBefore(currentTime)) {
            return true;
        }
        return false;
    }

    public Product addPurchasedStock(Long value) {
        Long stock = this.purchasedStock + value;
        SaleStatus status = this.status;
        Boolean isOver = isTotalStockOver(stock);
        if (isOver) {
            status = SaleStatus.SOLDOUT;
        }
        return Product.UpdateProductBuilder()
                .status(status)
                .productId(this.productId)
                .purchasedStock(stock)
                .build();
//        return Product.AddProductBuilder()
//                .status(status)
//                .name(this.name)
//                .description(this.description)
//                .price(this.price)
//                .totalStock(this.totalStock)
//                .personalLimitAmount(this.personalLimitAmount)
//                .startDealTime(this.startDealTime)
//                .endDealTime(this.endDealTime)
//                .purchasedStock(stock)
//                .build();
    }


}
