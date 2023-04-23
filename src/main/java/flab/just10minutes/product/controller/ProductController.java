package flab.just10minutes.product.controller;

import flab.just10minutes.product.domain.Product;
import flab.just10minutes.product.dto.AddProductRequest;
import flab.just10minutes.product.dto.ProductDto;
import flab.just10minutes.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<HttpStatus> addProduct(@RequestBody @Valid AddProductRequest addRequest) {
        productService.saveProduct(addRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping("/{productId}")
//    public ResponseEntity<HttpStatus> updateProduct(@RequestBody @Valid ProductDto productDto) {
//        Product product = ProductDto.toUpdateDomain(productDto);
//
//        productService.updateProduct(productDto);
//
//    }
}
