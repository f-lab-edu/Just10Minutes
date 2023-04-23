package flab.just10minutes.product.service;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.dto.AddProductRequest;
import flab.just10minutes.product.dto.ProductDto;

public interface ProductService {
    void saveProduct(AddProductRequest addProduct);

//    Product findProductById(Long id);
//
//    void updateProduct(Product product);
}
