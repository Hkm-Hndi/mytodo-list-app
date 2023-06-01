package com.hndi.mytodo.service;

import com.hndi.mytodo.model.Item;
import com.hndi.mytodo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public List<Item> getItemsByCategory(String categoryId) {
        return itemRepository.findByCategoryId(categoryId);
    }

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {

        return itemRepository.findAll();
    }

    @Override
    public Item getItem(String id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public Item updateItem(String id, Item item) {

        Item itemDB =itemRepository.findById(id).get();

        if (Objects.nonNull(itemDB.getName()) &&
                !"".equalsIgnoreCase(item.getName()))
            itemDB.setName(item.getName());

        if (Objects.nonNull(itemDB.getDescription()) &&
                !"".equalsIgnoreCase(item.getDescription()))
            itemDB.setDescription(item.getDescription());

        if (Objects.nonNull(itemDB.getCategoryId()) &&
                !"".equalsIgnoreCase(item.getCategoryId()))
            itemDB.setCategoryId(item.getCategoryId());

        return itemRepository.save(itemDB);
    }

    @Override
    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}
