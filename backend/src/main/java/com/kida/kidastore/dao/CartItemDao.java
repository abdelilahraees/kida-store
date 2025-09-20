package com.kida.kidastore.dao;


import com.kida.kidastore.models.entity.CartItem;
import com.kida.kidastore.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemDao {

    private CartItemRepository cartItemRepository;

    public CartItemDao(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }


    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public Optional<CartItem> readCartItem(long id) {
        return cartItemRepository.findById(id);
    }


    public CartItem updateCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }


    public void deleteCartItem(long id) {
        cartItemRepository.deleteById(id);

    }


    public List<CartItem> getAllCartItem() {
        return cartItemRepository.findAll();
    }
}
