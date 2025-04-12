package com.salam.ecommerce.order;

import com.salam.ecommerce.customer.CustomerClient;
import com.salam.ecommerce.exception.BusinessException;
import com.salam.ecommerce.kafka.OrderConfirmation;
import com.salam.ecommerce.kafka.OrderProducer;
import com.salam.ecommerce.orderline.OrderLineRequest;
import com.salam.ecommerce.orderline.OrderLineService;
import com.salam.ecommerce.product.ProductClient;
import com.salam.ecommerce.product.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(OrderRequest request) {
        //check customer
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order with customer id " + request.customerId()));

        //purchase products
        var purchasedProducts = productClient.purchaseProducts(request.products());

        //persist order
        var order = orderRepository.save(orderMapper.toOrder(request));

        //persist order-line
        for (PurchaseRequest purchaseRequest: request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        //start payment process

        //send order confirmation
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }
}
