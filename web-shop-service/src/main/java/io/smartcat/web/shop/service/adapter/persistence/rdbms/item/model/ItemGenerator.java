package io.smartcat.web.shop.service.adapter.persistence.rdbms.item.model;

import io.smartcat.web.shop.service.domain.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Class responsible for generating {@link Item} objects.
 */
public abstract class ItemGenerator {

    public static Map<String, Item> initializeItemRepository() {
        return items().stream().collect(toMap(Item::getId, identity()));
    }

    private static List<Item> items() {
        final List<Item> items = new ArrayList<>();
        items.addAll(shirts());
        items.addAll(jeans());
        items.addAll(accessories());
        return items;
    }

    private static List<Item> shirts() {
        final String category = "Shirts";
        return Arrays.asList(
                new Item("s0", category, "Adidas - 0", 24.99d),
                new Item("s1", category, "Adidas - 1", 99.00d),
                new Item("s2", category, "Adidas - 2", 19.99d),
                new Item("s3", category, "Nike - 3", 30.49),
                new Item("s4", category, "Nike - 4", 24.99d),
                new Item("s5", category, "Nike - 5", 24.99d),
                new Item("s6", category, "Puma - 6", 35.99),
                new Item("s7", category, "Puma - 7", 24.99d),
                new Item("s8", category, "Puma - 8", 24.99d),
                new Item("s9", category, "Puma - 9", 35.99)
        );
    }

    private static List<Item> jeans() {
        final String category = "Jeans";
        return Arrays.asList(
                new Item("j0", category, "Jeans - 0", 24.99d),
                new Item("j1", category, "Jeans - 1", 99.00d),
                new Item("j2", category, "Jeans - 2", 19.99d),
                new Item("j3", category, "Jeans - 3", 30.49),
                new Item("j4", category, "Jeans - 4", 24.99d),
                new Item("j5", category, "Jeans - 5", 24.99d),
                new Item("j6", category, "Jeans - 6", 35.99),
                new Item("j7", category, "Jeans - 7", 24.99d),
                new Item("j8", category, "Jeans - 8", 24.99d),
                new Item("j9", category, "Jeans - 9", 35.99)
        );
    }

    private static List<Item> accessories() {
        final String category = "Accessories";
        return Arrays.asList(
                new Item("a0", category, "Watch - 0", 24.99d),
                new Item("a1", category, "Watch - 1", 99.00d),
                new Item("a2", category, "Watch - 2", 19.99d),
                new Item("a3", category, "Hat - 3", 30.49),
                new Item("a4", category, "Hat - 4", 24.99d),
                new Item("a5", category, "Hat - 5", 24.99d),
                new Item("a6", category, "Hat - 6", 35.99),
                new Item("a7", category, "Bracelet - 7", 24.99d),
                new Item("a8", category, "Bracelet - 8", 24.99d),
                new Item("a9", category, "Bracelet - 9", 35.99)
        );
    }
}
