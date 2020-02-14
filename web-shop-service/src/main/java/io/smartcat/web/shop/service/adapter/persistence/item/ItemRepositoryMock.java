package io.smartcat.web.shop.service.adapter.persistence.item;

import io.smartcat.web.shop.service.adapter.persistence.item.model.ItemGenerator;
import io.smartcat.web.shop.service.domain.item.Item;
import io.smartcat.web.shop.service.domain.item.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Mocked Item Repository implemented for the sake of simplicity.
 */
@Repository
public class ItemRepositoryMock implements ItemRepository {

    private final Map<String, Item> items;

    public ItemRepositoryMock() {
        items = ItemGenerator.initializeItemRepository();
    }

    @Override
    public void save(final Item item) {
        items.put(item.getId(), item);
    }

    @Override
    public Optional<Item> findById(final String id) {
        return Optional.ofNullable(items.get(id));
    }

    @Override
    public List<Item> findAll() {
        return items.values().stream().collect(toList());
    }

    @Override
    public List<Item> findByCategory(final String category) {
        return items.values().stream().filter(item -> item.getCategory().equals(category)).collect(toList());
    }

    @Override
    public void remove(final String id) {
        items.remove(id);
    }
}
