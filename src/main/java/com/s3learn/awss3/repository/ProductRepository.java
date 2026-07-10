package com.s3learn.awss3.repository;

import com.s3learn.awss3.entity.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<Product> productTable;

    @PostConstruct
    public void init(){
        productTable=dynamoDbEnhancedClient.table("Product", TableSchema.fromBean(Product.class));
    }

    public Product save(Product product){
        productTable.putItem(product);
        return product;
    }

    public Product findById(String id){
        return productTable.getItem(r->r.key(k->k.partitionValue(id)));
    }

    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
//        productTable.scan().items().forEach(products::add);
        productTable.scan().items().forEach(product->products.add(product));
        return products;
    }

    public void deleteById(String id){
        productTable.deleteItem(r->r.key(k->k.partitionValue(id)));
    }
}
