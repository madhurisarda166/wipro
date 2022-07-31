package com.shopforhome.web.rest.category;

import com.shopforhome.entity.category.Category;
import com.shopforhome.response.ActionResponse;
import com.shopforhome.service.domain.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryResources {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ActionResponse> save(@RequestBody Category category) {
        categoryService.save(category);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(false);
        response.setResult(null);
        response.setMessage("Category saved successfully");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<ActionResponse> edit(@RequestBody Category category) {
        categoryService.save(category);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(false);
        response.setResult(null);
        response.setMessage("Category saved successfully");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ActionResponse> delete(@RequestParam("id") long id) {
        categoryService.delete(id);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(false);
        response.setResult(null);
        response.setMessage("Category saved successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        Page<Category> page=categoryService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }


}
