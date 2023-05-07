package flab.just10minutes.product.repository;

import flab.just10minutes.product.domain.Purchase;
import flab.just10minutes.product.dto.PurchaseDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PurchaseDao {

    int save(Purchase purchase);

    int delete(Long id);

    int findByPurchase(Purchase purchase);

    List<PurchaseDto> findByProductId(Long productId);

    List<PurchaseDto> findByMemberUniqueId(Long memberUniqueId);


}