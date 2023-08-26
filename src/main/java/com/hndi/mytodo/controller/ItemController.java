package com.hndi.mytodo.controller;

import com.hndi.mytodo.model.Item;
import com.hndi.mytodo.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/todos")
public class ItemController {
    private final Logger logger= LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    @GetMapping
    private List<Item> getItemsByCategory(@RequestParam(name="category", required = false) String categoryId){
        logger.trace("getting all items of category "+ categoryId);
        if (Objects.nonNull(categoryId))
            return itemService.getItemsByCategory(categoryId);
        else
            return itemService.getAllItems();

    }

    @GetMapping("{id}")
    public Item getItem(@PathVariable("id") String id) {
        logger.trace("getting item "+ id);
        return itemService.getItem(id);
    }

    @PostMapping
    private void addItem(@RequestBody Item item) {
        logger.trace("adding item "+ item.getName());
        itemService.addItem(item);
    }

    @PutMapping("{id}")
    private Item updateItem(@PathVariable("id") String id, @RequestBody Item item) {
        logger.trace("updaing item "+ id);
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("{id}")
    private void deleteItem(@PathVariable("id") String id) {
        logger.trace("deleting item "+ id);
        itemService.deleteItem(id);
    }
}
