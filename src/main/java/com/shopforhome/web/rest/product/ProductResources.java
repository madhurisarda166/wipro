package com.shopforhome.web.rest.product;

import com.shopforhome.entity.product.Product;
import com.shopforhome.response.ActionResponse;
import com.shopforhome.service.domain.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/product")
public class ProductResources {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ActionResponse> save(@RequestBody Product product) {
        long productId=productService.save(product);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(false);
        response.setResult(productId);
        response.setMessage("Product added successfully");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/image-upload/{productId}")
    public ResponseEntity<ActionResponse> uploadProductImage(@PathVariable("productId")long productId, @RequestParam("files")MultipartFile []files) throws IOException {
        productService.uploadImage(productId,files);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(true);
        response.setResult(null);
        response.setMessage(null);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/edit")
    public ResponseEntity<ActionResponse> edit(@RequestBody Product product) {
        productService.save(product);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(false);
        response.setResult(null);
        response.setMessage("Product edited successfully");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ActionResponse> delete(@PathVariable("id") long id) {
        productService.delete(id);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(false);
        response.setResult(null);
        response.setMessage("Product deleted successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
        Page<Product> page=productService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping("/bulk-upload")
    public ResponseEntity<ActionResponse> bulkUpload(MultipartFile file) throws IOException {
        productService.bulkProductUpload(file);
        ActionResponse response=new ActionResponse();
        response.setSuccessful(true);
        response.setException(false);
        response.setResult(null);
        response.setMessage("Products uploaded successfully");
        return ResponseEntity.ok().body(response);

    }


}
