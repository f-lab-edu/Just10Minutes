package flab.just10minutes.product.service;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.domain.SaleStatus;
import flab.just10minutes.product.dto.AddProductRequest;
import flab.just10minutes.product.dto.ProductDto;
import flab.just10minutes.product.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public List<ProductDto> findByStatus(SaleStatus status) {
        return productDao.findByStatus(status);
    }

    @Override
    public ProductDto findById(Long productId) {
        return ProductDto.builder().product(productDao.findById(productId)).build();
    }
//
//    @Override
//    public void updateProduct(Product product) {
//    }


}
