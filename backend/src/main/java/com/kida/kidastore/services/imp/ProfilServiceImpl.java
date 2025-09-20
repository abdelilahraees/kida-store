package com.kida.kidastore.services.imp;

import com.kida.kidastore.dao.*;
import com.kida.kidastore.exception.ResourceAlreadyExistException;
import com.kida.kidastore.exception.ResourceNotFoundException;
import com.kida.kidastore.models.dto.Request.AddressDtoRequest;
import com.kida.kidastore.models.dto.Request.CustomerDtoRequest;
import com.kida.kidastore.models.dto.Request.OrderAddressDtoReq;
import com.kida.kidastore.models.dto.Response.AddressDtoResponse;
import com.kida.kidastore.models.dto.Response.CartDtoResponse;
import com.kida.kidastore.models.dto.Response.InvoiceDtoResponse;
import com.kida.kidastore.models.dto.Response.OrderDtoResponse;
import com.kida.kidastore.models.entity.*;
import com.kida.kidastore.models.mappers.AddressMapper;
import com.kida.kidastore.models.mappers.CartMapper;
import com.kida.kidastore.models.mappers.OrderItemMapper;
import com.kida.kidastore.models.mappers.OrderMapper;
import com.kida.kidastore.services.IInvoiceService;
import com.kida.kidastore.services.IProductService;
import com.kida.kidastore.services.IProfilService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfilServiceImpl implements IProfilService {

    private IInvoiceService invoiceService;
    private CustomerDao customerDao;
    private OrderDao orderDao;
    private IProductService productService;
    private AddressDao addressDao;
    private AddressMapper addressMapper;
    private OrderMapper orderMapper;
    private CartDao cartDao;
    private CartItemDao cartItemDao;
    private CartMapper cartMapper;
    private OrderItemMapper orderItemMapper;

    public ProfilServiceImpl(CustomerDao customerDao, OrderDao orderDao, IProductService productService, AddressDao addressDao, AddressMapper addressMapper, OrderMapper orderMapper, CartDao cartDao, CartItemDao cartItemDao, CartMapper cartMapper, OrderItemMapper orderItemMapper, IInvoiceService iInvoiceService) {
        this.customerDao = customerDao;
        this.orderDao = orderDao;
        this.productService = productService;
        this.addressDao = addressDao;
        this.addressMapper = addressMapper;
        this.orderMapper = orderMapper;
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
        this.cartMapper = cartMapper;
        this.orderItemMapper = orderItemMapper;
        this.invoiceService = iInvoiceService;
    }

    Cart getCartByCurrentCustomer() {
        Customer customer = this.getCurrentCustomer();
        return customer.getCart();
    }

    boolean isCartItemExists(Long productId) {
        Cart cart = this.getCartByCurrentCustomer();
        return cart.getCartItems().stream().anyMatch(cartItem -> cartItem.getProduct().getId().equals(productId));
    }

    public Customer getCurrentCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof Jwt)) {
            System.out.println(authentication.getPrincipal().toString());
        }
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String customerId = jwt.getSubject();
        Optional<Customer> customer = this.customerDao.readCustomer(customerId);
        System.out.println(customerId);
        System.out.println(customer.isEmpty());
        if (customer.isEmpty()) {
            String username = jwt.getClaimAsString("preferred_username");
            String email = jwt.getClaimAsString("email");
            System.err.println("customerId"+customerId);
            System.err.println("customerUsername"+username);
            System.err.println("customerEmail"+email);
            Customer customersaved = Customer.builder()
                    .id(customerId)
                    .email(email)
                    .username(username)
                    .cart(new Cart())
                    .build();
            this.customerDao.createCustomer(customersaved);
        }
        return this.customerDao.readCustomer(customerId).orElseThrow(()->new ResourceNotFoundException("not Found"));
    }

    @Override
    public AddressDtoResponse addAddress(AddressDtoRequest addressDto) {
        Customer customer = this.getCurrentCustomer();
        boolean addressExists = customer.getAddressList().stream().anyMatch(address -> address.getCity().equalsIgnoreCase(addressDto.getCity()) && address.getStreet().equalsIgnoreCase(addressDto.getStreet()) && address.getHouseNumber().equalsIgnoreCase(addressDto.getHouseNumber()));
        if (addressExists) {
            throw new ResourceAlreadyExistException("Address already exists for this customer");
        }
        Address newAddress = addressMapper.toEntity(addressDto);
        newAddress.setCustomer(this.getCurrentCustomer());
        Address savedaddress = addressDao.createAddress(newAddress);
        return addressMapper.toDto(savedaddress);
    }


    @Override
    public void removeAddress(Long id) {
        Optional<Address> addressToRemove = addressDao.readAddress(id);
        addressToRemove.ifPresent(address -> this.getCurrentCustomer().removeAddress(address));
    }

    @Override
    public AddressDtoResponse updateAddress(Long id, AddressDtoRequest addressDto) {
        Address address = addressDao.readAddress(id).orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());

        address = addressDao.updateAddress(address);
        return addressMapper.toDto(address);
    }


    @Override
    public AddressDtoResponse getAddressById(Long id) {
        return addressDao.readAddress(id).map(addressMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id));
    }

    @Override
    public List<AddressDtoResponse> getAllAddress() {
        return this.getCurrentCustomer().getAddressList().stream().map(addressMapper::toDto).collect(Collectors.toList());
    }


    public void clearCart() {
        Cart cart = this.getCartByCurrentCustomer();
        cart.clearCart();
        cartDao.updateCart(cart);

    }

    @Override
    public CartDtoResponse getCart() {
        Cart cart = this.getCartByCurrentCustomer();
        return cartMapper.toDto(cart);
    }

    @Override
    public void addCartItem(Long productId) {
        if (!isCartItemExists(productId)) {
            Cart cart = this.getCartByCurrentCustomer();
            CartItem cartItem = new CartItem();
            Product product = productService.readProductEntity(productId);
            cartItem.setProduct(product);
            cartItemDao.createCartItem(cartItem);
            cart.addCartItem(cartItem);
            cartDao.updateCart(cart);
        }
    }

    @Override
    public void removeCartItem(Long itemId) {
        Cart cart = this.getCartByCurrentCustomer();
        Optional<CartItem> cartItem = cartItemDao.readCartItem(itemId);
        if (cartItem.isPresent()) {
            cart.removeCartItem(cartItem.get());
            cartDao.updateCart(cart);
        }
    }

    @Override
    public void updateCartItem(Long itemId, Long quantity) {
        Cart cart = this.getCartByCurrentCustomer();
        CartItem cartItem = cartItemDao.readCartItem(itemId).orElseThrow(() -> new ResourceNotFoundException("CartItem not found for itemId: " + itemId));
        cartItem.setQuantity(quantity);
        cartItemDao.updateCartItem(cartItem);
        cartDao.updateCart(cart);
    }

    @Transactional
    @Override
    public OrderDtoResponse addOrder(OrderAddressDtoReq orderAddressDtoReq) {
        OrderAddress orderAddress = OrderAddress.builder()
                .city(orderAddressDtoReq.getCity())
                .street(orderAddressDtoReq.getStreet())
                .houseNumber(orderAddressDtoReq.getHouseNumber())
                .country(orderAddressDtoReq.getCountry())
                .postCode(orderAddressDtoReq.getPostCode())
                .build();
        Order order = new Order();
        Cart cart = this.getCartByCurrentCustomer();
        if (cart.getCartItems().isEmpty()) {
            throw new ResourceNotFoundException("Cart is empty, cannot create order");
        }
        order.setTotal(cart.getTotal());
        order.setAddress(orderAddress);
        List<OrderItem> orderItemList = orderItemMapper.cartItemsToOrderItems(cart.getCartItems());
        order.setOrderItems(orderItemList);
        order.setCreatedAt(new Date());
        this.getCurrentCustomer().addOrder(order);
        order = orderDao.createOrder(order);
        InvoiceDtoResponse invoice = invoiceService.createInvoice(order);
        System.out.println("Invoice created: " + invoice.getId());
        this.clearCart();
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDtoResponse> getAllOrders() {
        return this.getCurrentCustomer().getOrder().stream().sorted(Comparator.comparing(Order::getId).reversed()).map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDtoResponse getOrderById(Long orderId) {
        return this.getCurrentCustomer().getOrder().stream().filter(order -> order.getId().equals(orderId)).findFirst().map(orderMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public InvoiceDtoResponse getInvoiceByOrderId(Long orderId) {
        return invoiceService.findInvoiceByOrderId(orderId);
    }

    @Override
    public Integer getItemsCount() {
        return this.getCurrentCustomer().getCart().getCartItems().size();
    }


}
