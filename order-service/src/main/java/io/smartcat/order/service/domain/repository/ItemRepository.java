package io.smartcat.order.service.domain.repository;


import io.smartcat.order.service.adapter.persistence.item.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    void save(Item item);

    Optional<Item> findById(String id);

    List<Item> findAll();

    List<Item> findByCategory(String category);

    void remove(String id);
}
