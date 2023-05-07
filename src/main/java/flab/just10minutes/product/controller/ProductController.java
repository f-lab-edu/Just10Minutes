package flab.just10minutes.product.controller;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.domain.SaleStatus;
import flab.just10minutes.product.dto.AddProductRequest;
import flab.just10minutes.product.dto.ProductDto;
import flab.just10minutes.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<HttpStatus> addProduct(@RequestBody @Valid AddProductRequest addRequest) {
        productService.saveProduct(addRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductDto>> getProductsByStatus(@PathVariable("status") SaleStatus status) {
        List<ProductDto> products = productService.findByStatus(status);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductDetail(@PathVariable("productId") Long productId) {
        ProductDto product = productService.findById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
//    @PutMapping("/{productId}")
//    public ResponseEntity<HttpStatus> updateProduct(@RequestBody @Valid ProductDto productDto) {
//        Product product = ProductDto.toUpdateDomain(productDto);
//
//        productService.updateProduct(productDto);
//
//    }
}
