package io.smartcat.order.service.domain.service;

import io.smartcat.order.service.adapter.persistence.item.model.Item;
import io.smartcat.order.service.domain.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void save(final Item item) {
        itemRepository.save(item);
    }

    public Optional<Item> findById(final String id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findByCategory(final String category) {
        return itemRepository.findByCategory(category);
    }

    public void remove(final String id) {
        itemRepository.remove(id);
    }
}
