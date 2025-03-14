package com.harshatrainings.prodcutservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/products")
    public List<Product> getALlProducts(@RequestHeader Map<String, String> headers) {
        System.out.println(headers.get("authorization"));
        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@RequestHeader Map<String, String> headers, @PathVariable Integer id) {
        return productService.getProducts().stream().filter(p-> Objects.equals(p.getPid(), id))
                .findAny().orElseThrow(()->new RuntimeException("Product Not Found"));
    }

    @PostMapping("/saveorder")
    public String saveOrder(@RequestHeader Map<String, String> reqheaders) {

        Product product1 = new Product(1,"Books",100);
        Product product2 = new Product(2,"Garments",200);
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerName("vivek");
        orderDto.setProducts(List.of(product1,product2));
        
        restTemplate.postForObject("http://localhost:8089/saveorder", orderDto, OrderDto.class);
        return "OrderSaved";

    }

}
