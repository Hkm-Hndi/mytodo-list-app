package com.hndi.mytodo.controller;

import com.hndi.mytodo.model.Item;
import com.hndi.mytodo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/todos")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    private List<Item> getItemsByCategory(@RequestParam(name="category", required = false) String categoryId){
        if (Objects.nonNull(categoryId))
            return itemService.getItemsByCategory(categoryId);
        else
            return itemService.getAllItems();

    }

    @GetMapping("{id}")
    public Item getItem(@PathVariable("id") String id) {
        return itemService.getItem(id);
    }

    @PostMapping
    private void addItem(@RequestBody Item item) {
        itemService.addItem(item);
    }

    @PutMapping("{id}")
    private Item updateItem(@PathVariable("id") String id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("{id}")
    private void deleteItem(@PathVariable("id") String id) {
        itemService.deleteItem(id);
    }
}
