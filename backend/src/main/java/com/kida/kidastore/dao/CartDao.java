package com.kida.kidastore.dao;

import com.kida.kidastore.dao.CartDao;
import com.kida.kidastore.models.entity.Cart;
import com.kida.kidastore.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDao {

    private CartRepository cartRepository;

    public CartDao(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }


    public Optional<Cart> readCart(long id) {
        return cartRepository.findById(id);
    }


    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(long id) {
        cartRepository.deleteById(id);

    }


    public Optional<Cart> findCartByCustomerId(String customerId) {
        return cartRepository.findCartByCustomerId(customerId);
    }


    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }
}
