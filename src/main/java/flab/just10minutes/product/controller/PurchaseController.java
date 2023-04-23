package flab.just10minutes.product.controller;

import flab.just10minutes.product.dto.PurchaseRequest;
import flab.just10minutes.product.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<HttpStatus> purchase(@RequestBody PurchaseRequest purchaseRequest) {
        purchaseService.purchase(purchaseRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
