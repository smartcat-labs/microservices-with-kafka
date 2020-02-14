package io.smartcat.web.shop.service.adapter.http;

import io.smartcat.web.shop.service.domain.item.Item;
import io.smartcat.web.shop.service.domain.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/item/")
public class ItemHttpAdapter {

    private final ItemService itemService;

    @Autowired
    public ItemHttpAdapter(final ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(path = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createItem(@RequestBody final Item item) {
        itemService.save(item);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{itemId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getItem(@PathVariable final String itemId) {
        return itemService.findById(itemId).map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("There is no item with id = " + itemId + "!"));
    }

    @DeleteMapping(path = "/{itemId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteItem(@PathVariable final String itemId) {
        itemService.remove(itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{itemId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateItem(@PathVariable final String itemId, @RequestBody final Item itemRequest) {
        return itemService.findById(itemId).map(item -> {
            itemService.update(itemId, itemRequest);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("There is no item with id = " + itemId + "!"));
    }
}
