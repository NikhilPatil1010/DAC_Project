package com.backend.service;



import com.backend.dto.*;
import com.backend.entity.*;
import com.backend.exception.ResourceNotFoundException;
import com.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final InventoryLogRepository inventoryLogRepository;
    
    public OrderResponse createOrder(Long userId, OrderRequest request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        CartResponse cartResponse = cartService.getCart(userId);
        if (cartResponse.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        
        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(cartResponse.getTotal());
        order.setShippingAddressJson(request.getShippingAddress());
        order.setStatus(Order.OrderStatus.PENDING);
        
        Order savedOrder = orderRepository.save(order);
        
        // Create order items
        for (CartItemResponse item : cartResponse.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            
            Product product = productRepository.findById(item.getProduct().getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());
            
            orderItemRepository.save(orderItem);
            
            // Update product stock
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
            
            // Log inventory change
            InventoryLog log = new InventoryLog();
            log.setProduct(product);
            log.setQuantityChanged(-item.getQuantity());
            log.setChangeType(InventoryLog.ChangeType.SALE);
            log.setNotes("Order #" + savedOrder.getOrderId());
            inventoryLogRepository.save(log);
        }
        
        // Create payment
        Payment payment = new Payment();
        payment.setOrder(savedOrder);
        payment.setAmount(savedOrder.getTotalAmount());
        payment.setPaymentMethod(Payment.PaymentMethod.valueOf(request.getPaymentMethod()));
        payment.setPaymentStatus(Payment.PaymentStatus.PENDING);
        
        if (request.getPaymentMethod().equals("COD")) {
            payment.setPaymentStatus(Payment.PaymentStatus.COMPLETED);
        }
        
        paymentRepository.save(payment);
        
        // Clear cart
        cartService.clearCart(userId);
        
        return convertToResponse(savedOrder);
    }
    
    public List<OrderResponse> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserUserIdOrderByOrderDateDesc(userId);
        return orders.stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    public OrderResponse getOrderById(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        
        if (!order.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized access to order");
        }
        
        return convertToResponse(order);
    }
    
    public OrderResponse updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        
        order.setStatus(Order.OrderStatus.valueOf(status));
        Order updatedOrder = orderRepository.save(order);
        
        return convertToResponse(updatedOrder);
    }
    
    private OrderResponse convertToResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus().name());
        response.setShippingAddress(order.getShippingAddressJson());
        
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(order.getUser().getUserId());
        userResponse.setName(order.getUser().getName());
        userResponse.setEmail(order.getUser().getEmail());
        response.setUser(userResponse);
        
        List<OrderItemResponse> items = order.getOrderItems().stream()
            .map(this::convertItemToResponse)
            .collect(Collectors.toList());
        response.setItems(items);
        
        if (order.getPayment() != null) {
            PaymentResponse paymentResponse = new PaymentResponse();
            paymentResponse.setPaymentId(order.getPayment().getPaymentId());
            paymentResponse.setAmount(order.getPayment().getAmount());
            paymentResponse.setPaymentMethod(order.getPayment().getPaymentMethod().name());
            paymentResponse.setPaymentStatus(order.getPayment().getPaymentStatus().name());
            paymentResponse.setPaymentDate(order.getPayment().getPaymentDate());
            paymentResponse.setTransactionId(order.getPayment().getTransactionId());
            response.setPayment(paymentResponse);
        }
        
        return response;
    }
    
    private OrderItemResponse convertItemToResponse(OrderItem item) {
        OrderItemResponse response = new OrderItemResponse();
        response.setOrderItemId(item.getOrderItemId());
        response.setQuantity(item.getQuantity());
        response.setPrice(item.getPrice());
        response.setSubtotal(item.getSubtotal());
        
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(item.getProduct().getProductId());
        productResponse.setName(item.getProduct().getName());
        productResponse.setImageUrl(item.getProduct().getImageUrl());
        productResponse.setBrand(item.getProduct().getBrand());
        response.setProduct(productResponse);
        
        return response;
    }
}