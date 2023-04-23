package flab.just10minutes.product.service;

import flab.just10minutes.member.domain.Member;
import flab.just10minutes.member.repository.MemberDao;
import flab.just10minutes.member.service.LoginService;
import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.domain.PurchaseActivity;
import flab.just10minutes.product.domain.PurchaseHistory;
import flab.just10minutes.product.domain.SaleStatus;
import flab.just10minutes.product.dto.PurchaseRequest;
import flab.just10minutes.product.repository.ProductDao;
import flab.just10minutes.product.repository.PurchaseActivityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{

    private final ProductDao productDao;
    private final PurchaseActivityDao purchaseActivityDao;
    private final MemberDao memberDao;
    private final LoginService loginService;

    @Override
    public void purchase(PurchaseRequest purchaseRequest) {
        loginService.loginValidate();

        Member member = memberDao.findByOpenId(purchaseRequest.getMemberOpenId());
        Product product = productDao.findById(purchaseRequest.getProductId());

        PurchaseActivity activity = validatePurchaseRule(product, member, purchaseRequest);

        Product updateProduct = product.addPurchasedStock(purchaseRequest.getAmount());
        int updateProductCount = productDao.update(updateProduct);
        if (updateProductCount != 1) {
            throw new IllegalStateException("상품 정보 수정 오류");
        }

        Member updateMember = member.minusBalance(product.getPrice());
        int updateMemberCount = memberDao.update(updateMember);
        if (updateMemberCount != 1) {
            throw new IllegalStateException("회원 정보 수정 오류");
        }

        int insertCount = purchaseActivityDao.save(activity);
        if (insertCount != 1) {
            throw new IllegalStateException("구매 이력 등록 오류");
        }
    }

    @Override
    public PurchaseHistory findProductHistory(Long id) {
        List<PurchaseActivity> activities =  purchaseActivityDao.findByProductId(id);
        return PurchaseHistory.builder()
                            .activities(activities)
                            .build();
    }
//
//    @Override
//    public PurchaseHistory findMemberHistory(Long openId) {
//        List<PurchaseActivity> activities = purchaseActivityDao.findByMemberId(openId);
//        return PurchaseHistory.builder()
//                            .activities(activities)
//                            .build();
//    }



    private PurchaseActivity validatePurchaseRule(Product product, Member member, PurchaseRequest purchaseRequest) {
        SaleStatus status =  product.getStatus();
        if (status != SaleStatus.ONSALE) {
            if (status != SaleStatus.SOLDOUT) throw new IllegalStateException("품절된 상품 입니다.");
            throw new IllegalStateException("판매 중인 상품이 아닙니다.");
        }

        Boolean totalAmountOver = product.isTotalStockOver();
        if (totalAmountOver) {
            throw new IllegalStateException("품절된 상품 입니다.");
        }

        Boolean personalLimitOver = product.isPersonalLimitOver(purchaseRequest.getAmount());
        if (personalLimitOver) {
            throw new IllegalStateException("상품 구입 제한 개수를 초과했습니다.");
        }

        PurchaseActivity activity =  PurchaseActivity.builder()
                                                    .member(member)
                                                    .product(product)
                                                    .amount(purchaseRequest.getAmount())
                                                    .build();

        Boolean alreadyPurchase = findProductHistory(product.getProductId()).isAlreadyPurchased(activity);
        if (alreadyPurchase) {
            throw new IllegalStateException("이미 구입한 상품 입니다.");
        }

        Boolean balanceOver = product.isBalanceOver(member);
        if (balanceOver) {
            throw new IllegalStateException("잔액을 초과 헀습니다.");
        }

        return activity;
    }






}

