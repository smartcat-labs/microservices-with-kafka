package io.smartcat.web.shop.service.domain.item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    /**
     *  Item will be created and persisted.
     *
     * @param item that should be persisted.
     */
    void save(Item item);

    /**
     *  Try to find item by id if exists.
     *
     * @param id of item.
     * @return Optional with {@link Item} if exists.
     */
    Optional<Item> findById(String id);


    /**
     *  Find all items.
     *
     * @return List of all persisted items.
     */
    List<Item> findAll();

    /**
     *  Try to find all items from provided category.
     *
     * @param category of item.
     * @return List of items from provided category.
     */
    List<Item> findByCategory(String category);


    /**
     *  Remove item by its id.
     *
     * @param id of item.
     */
    void remove(String id);
}
