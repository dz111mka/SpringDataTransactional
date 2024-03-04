package ru.chepikov.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.chepikov.dto.ProductIdsRequest;
import ru.chepikov.model.Order;
import ru.chepikov.service.OrderService;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/order")
public class Controller {

    OrderService orderService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> placeOrder(@PathVariable Long id, @RequestBody ProductIdsRequest productIdsRequest) throws Exception {
        Order order =  orderService.placeOrder(id, productIdsRequest.getProductIds());
        if (order != null) {
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
