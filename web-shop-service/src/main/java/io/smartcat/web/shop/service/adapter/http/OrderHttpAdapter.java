package io.smartcat.web.shop.service.adapter.http;

import io.smartcat.web.shop.service.domain.cart.CartService;
import io.smartcat.web.shop.service.domain.order.OrderService;
import io.smartcat.web.shop.service.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/order/")
public class OrderHttpAdapter {

    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;

    @Autowired
    public OrderHttpAdapter(
            final UserService userService,
            final CartService cartService,
            final OrderService orderService
    ) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @PostMapping(path = "create")
    public ResponseEntity<?> createOrderFromShoppingCart() {
        return userService.findCurrentUser().map(user -> {
            final String userId = user.getId();
            if (cartService.items(userId).isEmpty()) {
                throw new RuntimeException("Shopping Cart is empty. Can't create order!");
            }
            orderService.createAndPublishOrder(userId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("There is no current user!"));
    }

    @DeleteMapping(path = "cancel")
    public ResponseEntity<?> clearShoppingCart() {
        return userService.findCurrentUser().map(user -> {
            final String userId = user.getId();
            cartService.clear(userId);
            orderService.clearAndPublishOrder(userId);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("There is no current user!"));
    }
}
