package org.codingdojo.potterkata.contollers;

import org.codingdojo.potterkata.models.Order;
import org.codingdojo.potterkata.services.DefaultOrderService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@RestController
public class OrderController {
    private final DefaultOrderService orderService;

    public OrderController(DefaultOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    String Find(@PathVariable String id) throws HttpClientErrorException {
        Order order = this.orderService.find(UUID.fromString(id));
        if (order == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return order.toJSON().toString();
    }
}
