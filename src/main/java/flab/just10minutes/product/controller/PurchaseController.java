package flab.just10minutes.product.controller;

import flab.just10minutes.aop.MemberLoginCheck;
import flab.just10minutes.member.service.LoginService;
import flab.just10minutes.product.dto.PurchaseDto;
import flab.just10minutes.product.dto.PurchaseRequest;
import flab.just10minutes.product.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @MemberLoginCheck
    @PostMapping
    public ResponseEntity<HttpStatus> purchase(@RequestBody PurchaseRequest purchaseRequest) {
        purchaseService.purchase(purchaseRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/memberHistory/{memberUniqueId}")
    public ResponseEntity<List<PurchaseDto>> getMemberPurchaseHistory(@PathVariable("memberUniqueId") Long memberUniqueId) {
        List<PurchaseDto> memberPurchaseHistory = purchaseService.findMemberHistory(memberUniqueId);
        return new ResponseEntity<>(memberPurchaseHistory, HttpStatus.OK);
    }

    @GetMapping("/productHistory/{productId}")
    public ResponseEntity<List<PurchaseDto>> getProductPurchaseHistory(@PathVariable("productId") Long productId) {
        List<PurchaseDto> productPurchaseHistory = purchaseService.findProductHistory(productId);
        return new ResponseEntity<>(productPurchaseHistory, HttpStatus.OK);
    }
}
