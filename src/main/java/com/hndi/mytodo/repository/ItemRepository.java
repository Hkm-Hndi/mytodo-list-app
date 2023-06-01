package com.hndi.mytodo.repository;

import com.hndi.mytodo.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {

    public List<Item> findByCategoryId(String id);
}
