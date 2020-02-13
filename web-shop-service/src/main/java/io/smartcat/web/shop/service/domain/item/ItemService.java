package io.smartcat.web.shop.service.domain.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void save(final Item item) {
        item.setId(UUID.randomUUID().toString());
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

    public void update(final String itemId, final Item itemRequest) {
        itemRequest.setId(itemId);
        itemRepository.save(itemRequest);
    }
}
