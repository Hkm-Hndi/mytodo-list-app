package com.hndi.mytodo.service;

import com.hndi.mytodo.model.Item;

import java.util.List;

public interface ItemService {
    public List<Item> getItemsByCategory(String categoryId);
    public void addItem(Item item);
    public List<Item> getAllItems();
    public Item getItem(String id);
    public Item updateItem(String id, Item item);
    public void deleteItem(String id);


}
