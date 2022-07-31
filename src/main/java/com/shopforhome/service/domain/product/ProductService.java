package com.shopforhome.service.domain.product;

import com.shopforhome.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

    long save(Product product);

    void delete(long id);

    void uploadImage(long productId, MultipartFile []files) throws IOException;

    Page<Product> findAll(Pageable pageable);

    Product findById(long id);

    void bulkProductUpload(MultipartFile file) throws IOException;
}
