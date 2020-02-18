package io.smartcat.web.shop.service.adapter.cache;

import io.smartcat.web.shop.service.domain.cart.CartRepository;
import io.smartcat.web.shop.service.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.stream.Collectors.toList;

@Repository
public class CartInMemoryCache implements CartRepository {

    private final Map<String, List<Item>> shoppingCart;
    private final ReentrantReadWriteLock lock;

    public CartInMemoryCache() {
        shoppingCart = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    @Override
    public void add(final String userId, final Item item) {
        lock.writeLock().lock();
        try {
            Optional.ofNullable(shoppingCart.get(userId)).ifPresentOrElse(
                    userItems -> {
                        final List<Item> items = new ArrayList<>(userItems);
                        items.add(item);
                        shoppingCart.put(userId, items);
                    },
                    () -> shoppingCart.put(userId, Collections.singletonList(item))
            );
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void remove(final String userId, final String itemId) {
        lock.writeLock().lock();
        try {
            Optional.ofNullable(shoppingCart.get(userId))
                    .ifPresent(items -> {
                        final List<Item> newItems = items.stream()
                                .filter(item -> !item.getId().equals(itemId)).collect(toList());
                        shoppingCart.put(userId, newItems);
                    });
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void clear(final String userId) {
        lock.writeLock().lock();
        try {
            shoppingCart.remove(userId);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Item> items(final String userId) {
        lock.readLock().lock();
        try {
            return shoppingCart.get(userId);
        } finally {
            lock.readLock().unlock();
        }
    }
}
