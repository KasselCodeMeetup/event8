package de.flaviait.codemeetup.order;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderFeignService {

    @Autowired
    private OrderFeignClient orderFeignClient;

    @HystrixCommand(fallbackMethod = "fallback")
    public void takeOrder(OrderInput input) {
        input.validate();

        orderFeignClient.create(input.asEntity());
    }

    public List<OrderEntity> list() {
        return orderFeignClient.all();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public OrderEntity find(Long orderId) {
        return orderFeignClient.find(orderId);
    }

    public OrderEntity create() {
        return new OrderEntity("", null);
    }

    private List<OrderEntity> fallback() {
        return Collections.emptyList();
    }

    private OrderEntity fallback(Long id) {
        return null;
    }
}
