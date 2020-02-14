package io.smartcat.web.shop.service.domain.cart;

import io.smartcat.web.shop.service.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.smartcat.avro.events.item.ItemEventType.ADD_ITEM;
import static io.smartcat.avro.events.item.ItemEventType.REMOVE_ITEM;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemEvenPublisher itemEvenPublisher;
    private final ItemToItemEventMapper mapper;

    @Autowired
    public CartService(
            final CartRepository cartRepository,
            final ItemEvenPublisher itemEvenPublisher,
            final ItemToItemEventMapper mapper
    ) {
        this.cartRepository = cartRepository;
        this.itemEvenPublisher = itemEvenPublisher;
        this.mapper = mapper;
    }

    public void addToCartAndPublish(final String userId, final Item item) {
        cartRepository.add(userId, item);
        itemEvenPublisher.publishItemEvent(mapper.itemToItemEvent(userId, item, ADD_ITEM));
    }

    public void removeFromCartAndPublish(final String userId, final Item item) {
        cartRepository.remove(userId, item.getId());
        itemEvenPublisher.publishItemEvent(mapper.itemToItemEvent(userId, item, REMOVE_ITEM));
    }

    public void clear(final String userId) {
        cartRepository.clear(userId);
    }

    public List<Item> items(final String userId) {
        return Optional.ofNullable(cartRepository.items(userId)).orElse(Collections.emptyList());
    }
}
