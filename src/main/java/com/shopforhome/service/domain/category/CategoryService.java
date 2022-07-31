package com.shopforhome.service.domain.category;

import com.shopforhome.entity.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    void save(Category category);

    Page<Category> findAll(Pageable pageable);

    void delete(long id);

    Category findByName(String name);

}
