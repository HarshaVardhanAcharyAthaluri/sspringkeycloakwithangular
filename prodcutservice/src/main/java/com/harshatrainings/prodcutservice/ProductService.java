package com.harshatrainings.prodcutservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> productList = new ArrayList<Product>();

    public List<Product> getProducts() {
        Product product1 = new Product(1,"Books",100);
        Product product2 = new Product(2,"Mobile",200);
        Product product3 = new Product(3,"stationary",250);
        Product product4 = new Product(4,"Homeapplieance",300);
        Product product5 = new Product(5,"garments",400);
        Product product6 = new Product(6,"grooming",550);
        Product product7 = new Product(7,"automobile",700);
        return List.of(product1,product2,product3,product4,product5,product6,product7);
    }
}
