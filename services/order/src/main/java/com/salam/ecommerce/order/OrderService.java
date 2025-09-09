package com.salam.ecommerce.order;

import com.salam.ecommerce.customer.CustomerClient;
import com.salam.ecommerce.exception.BusinessException;
import com.salam.ecommerce.kafka.OrderConfirmation;
import com.salam.ecommerce.kafka.OrderProducer;
import com.salam.ecommerce.orderline.OrderLineRequest;
import com.salam.ecommerce.orderline.OrderLineService;
import com.salam.ecommerce.payment.PaymentClient;
import com.salam.ecommerce.payment.PaymentRequest;
import com.salam.ecommerce.product.ProductClient;
import com.salam.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public OrderResponse createOrder(OrderRequest request) {
        //check customer
        var customer = customerClient.findCustomerById(request.customerId());
        if (customer.isEmpty()) {
            throw new BusinessException("Customer not found");
        }

        //purchase products
        var product = productClient.getProduct(request.productId());
        if (product.availableQuantity() < request.quantity()) {
            throw new BusinessException("Not enough stock for product " + product.name());
        }

        //decrease stock
        productClient.decreaseStock(product.id(), request.quantity());

        //persist order
        Order order = Order.builder()
                .customerId(request.customerId())
                .productId(request.productId())
                .quantity(request.quantity())
                .status(OrderStatus.CREATED)
                .build();

        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getProductId(),
                order.getQuantity(),
                order.getStatus().name()
        );
    }

    public List<OrderResponse> findAll() {
        orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
        return null;
    }

    public OrderResponse findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find order with id " + id));
    }
}
