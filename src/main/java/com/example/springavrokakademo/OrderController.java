package com.example.springavrokakademo;

import com.example.springavrokakademo.models.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class OrderController {
    
    private final KafkaPublisher publisher;
    
    @PostMapping("/order")
    Mono<String> addOrder(@RequestBody OrderRequest request) {
        
        Order order = Order.newBuilder()
                .setOrderId(request.getOrderId())
                .setCustomerId(request.getCustomerId())
                .setSupplierId(request.getSupplierId())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setItems(request.getItems())
                .setPrice(request.getPrice())
                .setWeight(request.getWeight())
                .setAutomatedEmail(request.getAutomatedEmail())
                .build();
        
        return Mono.fromFuture(publisher.publish(order))
                .then(Mono.just("Published to Kafka"))
                .doOnError(thr -> {
                    System.out.println("An error occured :" + thr);
                });
    }
}
