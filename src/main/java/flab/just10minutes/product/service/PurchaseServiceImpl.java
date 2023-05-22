package flab.just10minutes.product.service;

import flab.just10minutes.lock.DistributedLock;
import flab.just10minutes.member.repository.MemberDao;
import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.domain.Purchase;
import flab.just10minutes.product.domain.SaleStatus;
import flab.just10minutes.product.dto.PurchaseDto;
import flab.just10minutes.product.dto.PurchaseRequest;
import flab.just10minutes.product.repository.ProductDao;
import flab.just10minutes.product.repository.PurchaseDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseServiceImpl implements PurchaseService{

    private final ProductDao productDao;
    private final PurchaseDao purchaseDao;
    private final MemberDao memberDao;


    @Override
    @Transactional(rollbackFor = {BadSqlGrammarException.class})
    public void purchase(PurchaseRequest purchaseRequest) {
//        Member member = memberDao.findByMemberUniqueId(purchaseRequest.getMemberUniqueId());
//        Product product = productDao.findById(purchaseRequest.getProductId());
//
//        Purchase purchase = validatePurchaseRule(product, member, purchaseRequest);
//
//
//        Product updateProduct = product.addPurchasedStock(purchaseRequest.getAmount());
//        int updateProductCount = productDao.updatePurchasedStock(updateProduct);
//        if (updateProductCount != 1) {
//            throw new IllegalStateException("상품 정보 수정 오류");
//        }
//
//        Member updateMember = member.minusBalance(product.getPrice());
//        int updateMemberCount = memberDao.updateMemberBalance(updateMember);
//        if (updateMemberCount != 1) {
//            throw new IllegalStateException("회원 정보 수정 오류");
//        }
//
//        int insertCount = purchaseDao.save(purchase);
//        if (insertCount != 1) {
//            throw new IllegalStateException("구매 이력 등록 오류");
//        }
    }

    @Override
    public List<PurchaseDto>  findProductHistory(Long productId) {
        return purchaseDao.findByMemberUniqueId(productId);
    }

    @Override
    public List<PurchaseDto>  findMemberHistory(Long memberUniqueId) {
        return purchaseDao.findByMemberUniqueId(memberUniqueId);
    }

//    private Purchase validatePurchaseRule(Product product, Member member, PurchaseRequest purchaseRequest) {
//        SaleStatus status =  product.getStatus();
//        if (status != SaleStatus.ONSALE) {
//            if (status != SaleStatus.SOLDOUT) throw new IllegalStateException("품절된 상품 입니다.");
//            throw new IllegalStateException("판매 중인 상품이 아닙니다.");
//        }
//
//        Boolean totalAmountOver = product.isTotalStockOver();
//        if (totalAmountOver) {
//            throw new IllegalStateException("품절된 상품 입니다.");
//        }
//
//        Boolean personalLimitOver = product.isPersonalLimitOver(purchaseRequest.getAmount());
//        if (personalLimitOver) {
//            throw new IllegalStateException("상품 구입 제한 개수를 초과했습니다.");
//        }
//
//        Purchase purchase =  Purchase.builder()
//                            .memberUniqueId(member.getUniqueId())
//                            .productId(product.getProductId())
//                            .amount(purchaseRequest.getAmount())
//                            .build();
//
//        int selectCount = purchaseDao.findByPurchase(purchase);
//
//        if (selectCount == 1) {
//            throw new IllegalStateException("이미 구입한 상품 입니다.");
//        }
//
//        Boolean balanceOver = product.isBalanceOver(member);
//        if (balanceOver) {
//            throw new IllegalStateException("잔액을 초과 헀습니다.");
//        }
//
//        return purchase;
//    }

    private Purchase validatePurchaseRule(Product product, PurchaseRequest purchaseRequest) {
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

        Purchase purchase =  Purchase.builder()
                .memberUniqueId(purchaseRequest.getMemberUniqueId())
                .productId(product.getProductId())
                .amount(purchaseRequest.getAmount())
                .build();

        int selectCount = purchaseDao.findByPurchase(purchase);

//        if (selectCount == 1) {
//            log.info("here1");
//            throw new IllegalStateException("이미 구입한 상품 입니다.");
//        }
        return purchase;
    }

    @Override
    @DistributedLock(key = "PRODUCT")
    @Transactional(rollbackFor = {BadSqlGrammarException.class})
    public void purchaseWithLock(PurchaseRequest purchaseRequest) {

        Product product = productDao.findById(purchaseRequest.getProductId());
        Purchase purchase = validatePurchaseRule(product, purchaseRequest);

        Product updateProduct = product.addPurchasedStock(purchaseRequest.getAmount());
        int updateProductCount = productDao.updatePurchasedStock(updateProduct);
        if (updateProductCount != 1) {
            throw new IllegalStateException("상품 정보 수정 오류");
        }
        int insertCount = purchaseDao.save(purchase);
        if (insertCount != 1) {
            throw new IllegalStateException("구매 이력 등록 오류");
        }
    }



}

