package flab.just10minutes.product.repository;

import flab.just10minutes.product.domain.PurchaseActivity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PurchaseActivityDao {

    int save(PurchaseActivity activity);

    int delete(Long id);

    List<PurchaseActivity> findByProductId(Long productId);

    List<PurchaseActivity> findByMemberUniqueId(Long memberUniqueId);

   // PurchaseActivity findById(Long id);






}