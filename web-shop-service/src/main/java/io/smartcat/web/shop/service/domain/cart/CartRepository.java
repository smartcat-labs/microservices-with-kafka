package io.smartcat.web.shop.service.domain.cart;

import io.smartcat.web.shop.service.domain.item.Item;

import java.util.List;

public interface CartRepository {

    /**
     *  Try to add item to shopping cart.
     *
     * @param userId of user who wants to add item to shopping cart.
     * @param item that should be added to shopping cart.
     */
    void add(String userId, Item item);

    /**
     *  Try to remove item to shopping cart.
     *
     * @param userId of user who wants to remove item to shopping cart.
     * @param itemId hat should be removed from shopping cart.
     */
    void remove(String userId, String itemId);

    /**
     * Try to remove all items from shopping cart.
     *
     * @param userId of user who wants to clear items from shopping cart.
     */
    void clear(String userId);

    /**
     *  Try to get all items from shopping cart.
     *
     * @param userId of user who created shopping cart.
     * @return List of items from shopping cart.
     */
    List<Item> items(String userId);
}
