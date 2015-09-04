package de.flaviait.codemeetup.order;

import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("order-service")
public interface OrderFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/orders/{orderId}", produces = "application/json")
    OrderEntity find(@PathVariable("orderId") Long orderId);

    @RequestMapping(method = RequestMethod.GET, value = "/orders", produces = "application/json")
    List<OrderEntity> all();

    @RequestMapping(method = RequestMethod.POST, value = "/orders", consumes = "application/json")
    OrderEntity create(@Param("order") OrderEntity entity);
}
