package com.hndi.mytodo.repository;

import com.hndi.mytodo.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {


}
