package com.shopforhome.service.domain.category.impl;

import com.shopforhome.entity.category.Category;
import com.shopforhome.repository.category.CategoryRepository;
import com.shopforhome.service.domain.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public void delete(long id) {
        Category category=findById(id);
        categoryRepository.delete(category);
    }

    private Category findById(long id) {
        Optional<Category> category=categoryRepository.findById(id);
        if(category.isPresent()) return category.get();
        return null;
    }

    public Category findByName(String name) {
        Category category=categoryRepository.findByName(name);
        return category;
    }
}
