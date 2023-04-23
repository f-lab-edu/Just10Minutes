package flab.just10minutes.product.repository;

import flab.just10minutes.product.domain.PurchaseActivity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PurchaseActivityDao {

    List<PurchaseActivity> findByProductId(Long id);

    List<PurchaseActivity> findByMemberId(Long id);

    PurchaseActivity findById(Long id);

    int save(PurchaseActivity activity);

    int update(PurchaseActivity activity);

    int delete(Long id);




}