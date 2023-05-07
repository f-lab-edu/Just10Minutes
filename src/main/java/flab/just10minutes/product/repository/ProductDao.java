package flab.just10minutes.product.repository;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.domain.SaleStatus;
import flab.just10minutes.product.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductDao {
    int save(Product product);

    Product findById(Long productId);

    int updatePurchasedStock(Product product);

    List<ProductDto> findByStatus(SaleStatus status);


   // List<Product> findAll();


}
