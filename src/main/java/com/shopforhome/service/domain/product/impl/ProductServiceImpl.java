package com.shopforhome.service.domain.product.impl;

import com.shopforhome.entity.category.Category;
import com.shopforhome.entity.product.Product;
import com.shopforhome.repository.product.ProductRepository;
import com.shopforhome.service.domain.category.CategoryService;
import com.shopforhome.service.domain.product.ProductService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    private final static Path root= Paths.get("src/main/resources/images");

    @Override
    public long save(Product product) {
        Product product1;
        if(product.getId()==null) {
            product1=productRepository.save(product);
        }
        else {
            product1=findById(product.getId());
            product.setImage1(product1.getImage1());
            product.setImage2(product1.getImage2());
            product.setImage3(product1.getImage3());
            product1=productRepository.save(product);
        }
        return product1.getId();
    }

    @Override
    public void uploadImage(long productId, MultipartFile[]files) throws IOException {
        Product product=findById(productId);
        List<String> paths=new ArrayList<>();
        for(MultipartFile file:files) {
            String path= UUID.randomUUID() +".png";
            Files.copy(file.getInputStream(),this.root.resolve(path));
            paths.add(path);
        }
        product.setImage1(paths.get(0));
        product.setImage2(paths.get(1));
        product.setImage3(paths.get(2));
        productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        Page<Product> productPage=productRepository.findAll(pageable);
        return productPage;
    }

    @Override
    public void delete(long id) {
        Product product=findById(id);
        productRepository.delete(product);
    }

    @Override
    public Product findById(long id) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isPresent()) return product.get();
        return null;
    }

    @Override
    public void bulkProductUpload(MultipartFile file) throws IOException {
        InputStream is=file.getInputStream();
        BufferedReader fileReader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
        CSVParser csvParser=new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        List<Product> productList=new ArrayList<>();
        Iterable<CSVRecord> csvRecords=csvParser.getRecords();
        for (CSVRecord csvRecord:csvRecords) {
            Product product=new Product();
            product.setName(csvRecord.get("Name"));
            product.setDescription(csvRecord.get("Description"));
            String []searchKeywords=csvRecord.get("searchKeywords").split(",");
            List<String> searchKeywordList=Arrays.asList(searchKeywords);
            Category category=categoryService.findByName(csvRecord.get("category"));
            product.setCategory(category);
            product.setPrice(new BigDecimal(csvRecord.get("price")));
            product.setGst(Float.parseFloat(csvRecord.get("gst")));
            product.setQty(Integer.parseInt(csvRecord.get("qty")));
            productRepository.save(product);
        }
    }

}
