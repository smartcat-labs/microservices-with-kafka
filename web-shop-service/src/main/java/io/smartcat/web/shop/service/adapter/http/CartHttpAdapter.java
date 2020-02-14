package io.smartcat.web.shop.service.adapter.http;

import io.smartcat.web.shop.service.domain.cart.CartService;
import io.smartcat.web.shop.service.domain.item.ItemService;
import io.smartcat.web.shop.service.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cart/")
public class CartHttpAdapter {

    private final UserService userService;
    private final CartService cartService;
    private final ItemService itemService;

    @Autowired
    public CartHttpAdapter(
            final UserService userService,
            final CartService cartService,
            final ItemService itemService
    ) {
        this.userService = userService;
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @PostMapping(path = "{itemId}/add")
    public ResponseEntity<?> addItemToShoppingCart(@PathVariable final String itemId) {
        return userService.findCurrentUser().map(user ->
                itemService.findById(itemId).map(item -> {
                    cartService.addToCartAndPublish(user.getId(), item);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RuntimeException("There is no item with id = " + itemId + "!"))
        ).orElseThrow(() -> new RuntimeException("There is no current user!"));
    }

    @DeleteMapping(path = "{itemId}/remove")
    public ResponseEntity<?> removeItemFromShoppingCart(@PathVariable final String itemId) {
        return userService.findCurrentUser().map(user ->
                itemService.findById(itemId).map(item -> {
                    cartService.removeFromCartAndPublish(user.getId(), item);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new RuntimeException("There is no item with id = " + itemId + "!"))
        ).orElseThrow(() -> new RuntimeException("There is no current user!"));
    }
}
