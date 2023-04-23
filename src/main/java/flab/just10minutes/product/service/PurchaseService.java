package flab.just10minutes.product.service;

import flab.just10minutes.product.domain.PurchaseHistory;
import flab.just10minutes.product.dto.PurchaseRequest;

public interface PurchaseService {

    void purchase(PurchaseRequest purchaseRequest);

    PurchaseHistory findProductHistory(Long id);
//
//    PurchaseHistory findMemberHistory(Long openId);
}