package de.flaviait.codemeetup.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderEntity> list() {
        return orderRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public OrderEntity find(@PathVariable Long id) {
        return orderRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public OrderEntity create(@RequestBody OrderEntity input) {
        return orderRepository.save(input);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        orderRepository.delete(id);
    }
}
