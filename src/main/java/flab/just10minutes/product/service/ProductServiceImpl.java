package flab.just10minutes.product.service;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.dto.AddProductRequest;
import flab.just10minutes.product.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;

    @Override
    public void saveProduct(AddProductRequest addProduct) {
        Product newProduct = AddProductRequest.toProductDomain(addProduct);
        int insertCount = productDao.save(newProduct);

        if (insertCount != 1) {
            throw new IllegalStateException("상품 등록 오류");
        }
    }

//    @Override
//    public Product findProductById(Long id) {
//        return productDao.findById(id);
//    }
//
//    @Override
//    public void updateProduct(Product product) {
//    }


}
