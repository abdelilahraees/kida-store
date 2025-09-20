package com.kida.kidastore.web;

import com.kida.kidastore.models.dto.Response.CartDtoResponse;
import com.kida.kidastore.services.IProfilService;
import com.kida.kidastore.shared.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile/cart")
public class ProfilCartController {
    private IProfilService profilService;

    public ProfilCartController(IProfilService profilService) {
        this.profilService = profilService;
    }


    @PostMapping("/cart-items")
    public ResponseEntity<GlobalResponse<Void>> addToCart(@RequestParam Long productId) {
        profilService.addCartItem(productId);
        return ResponseEntity.noContent().build();

    }


    @PutMapping("/cart-items/{id}")
    public ResponseEntity<GlobalResponse<Void>> updateCartItem(@PathVariable Long id, @RequestParam Long quantity) {
        profilService.updateCartItem(id, quantity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cart-items/{id}")
    public ResponseEntity<GlobalResponse<Void>> removeFromCart(@PathVariable Long id) {
        profilService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<CartDtoResponse>> getCart() {
        CartDtoResponse response = profilService.getCart();
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalResponse<>(response));
    }
    @GetMapping("/items-count")
    public int getItemsCount() {
       return this.profilService.getItemsCount();
    }

}
