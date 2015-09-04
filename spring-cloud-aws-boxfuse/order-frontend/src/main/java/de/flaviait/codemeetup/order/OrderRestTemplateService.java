package de.flaviait.codemeetup.order;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class OrderRestTemplateService {

    @Autowired
    private RestTemplate template;

    // @HystrixCommand(fallbackMethod = "fallback")
    public void takeOrder(OrderInput input) {
        input.validate();

        template.postForEntity("http://order-service/orders", input.asEntity(), Void.class);
    }

    // @HystrixCommand(fallbackMethod = "fallback")
    public List<OrderEntity> list() {
        return (List<OrderEntity>) template.getForObject("http://order-service/orders", List.class);
    }

    // @HystrixCommand(fallbackMethod = "fallback")
    public OrderEntity find(Long orderId) {
        OrderEntity result = template.getForObject("http://order-service/orders/{orderId}", OrderEntity.class, orderId);
        return result;
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
