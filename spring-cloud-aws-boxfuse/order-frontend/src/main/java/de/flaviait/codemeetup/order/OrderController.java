package de.flaviait.codemeetup.order;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderFeignService orderRestTemplateService;

    //@Autowired
    //private OrderRestTemplateService orderRestTemplateService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("orders", orderRestTemplateService.list());

        return "orders/list";
    }

    @RequestMapping("/{orderId}")
    public String edit(Model model, @PathVariable Long orderId) {
        OrderEntity output = orderRestTemplateService.find(orderId);

        if (output == null) {
            throw new NotFoundException();
        }

        model.addAttribute("order", output);
        return "orders/edit";
    }

    private String generateServerInfo() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("order-service", false);
        return instance.getHostName() + ":" + instance.getPort();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam("name") String name, @RequestParam("drink") DrinkType drink) {
        OrderInput input = new OrderInput();
        input.setDrink(drink);
        input.setName(name);

        orderRestTemplateService.takeOrder(input);
        return "redirect:/";
    }

    @ExceptionHandler(value = ServiceUnavailableException.class)
    public String serviceUnavailable() {
        return "orders/unavailable";
    }

    @ExceptionHandler(value = NotFoundException.class)
    public String noEntryFound() {
        return "orders/404";
    }

    @ModelAttribute
    public void addInfo(Model model) {
        String server = generateServerInfo();
        model.addAttribute("server", server);
    }
}
