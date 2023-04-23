package flab.just10minutes.product.repository;

import flab.just10minutes.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductDao {
    int save(Product product);

    Product findById(Long id);

    int update(Product product);

    List<Product> findAll();


}
