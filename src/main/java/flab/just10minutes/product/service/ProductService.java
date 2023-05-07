package flab.just10minutes.product.service;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.domain.SaleStatus;
import flab.just10minutes.product.dto.AddProductRequest;
import flab.just10minutes.product.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void saveProduct(AddProductRequest addProduct);

    List<ProductDto> findByStatus(SaleStatus status);

    ProductDto findById(Long productId);
//
//    void updateProduct(Product product);
}
