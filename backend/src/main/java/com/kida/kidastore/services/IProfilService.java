package com.kida.kidastore.services;


import com.kida.kidastore.models.dto.Request.AddressDtoRequest;
import com.kida.kidastore.models.dto.Request.OrderAddressDtoReq;
import com.kida.kidastore.models.dto.Response.AddressDtoResponse;
import com.kida.kidastore.models.dto.Response.CartDtoResponse;
import com.kida.kidastore.models.dto.Response.InvoiceDtoResponse;
import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.models.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProfilService {

    AddressDtoResponse addAddress(AddressDtoRequest addressDto);

    void removeAddress(Long id);

    List<AddressDtoResponse> getAllAddress();

    AddressDtoResponse updateAddress(Long id, AddressDtoRequest addressDto);

    AddressDtoResponse getAddressById(Long id);

    Customer getCurrentCustomer();


    CartDtoResponse getCart();

    void addCartItem(Long productId);

    void removeCartItem(Long itemId);


    void updateCartItem(Long itemId, Long quantity);

    void clearCart();
     Integer getItemsCount();


    OrderDtoResponse addOrder(OrderAddressDtoReq orderAddressDtoReq);


    List<OrderDtoResponse> getAllOrders();

    OrderDtoResponse getOrderById(Long orderId);

    InvoiceDtoResponse getInvoiceByOrderId(Long orderId);
}
