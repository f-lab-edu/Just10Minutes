package flab.just10minutes.product.service;

import flab.just10minutes.product.domain.PurchaseHistory;
import flab.just10minutes.product.dto.PurchaseDto;
import flab.just10minutes.product.dto.PurchaseRequest;

import java.util.List;

public interface PurchaseService {

    void purchase(PurchaseRequest purchaseRequest);

    List<PurchaseDto> findProductHistory(Long productId);

    List<PurchaseDto> findMemberHistory(Long memberUniqueId);


    void purchaseWithLock(PurchaseRequest purchaseRequest);
}