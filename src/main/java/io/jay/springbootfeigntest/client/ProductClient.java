package io.jay.springbootfeigntest.client;

import io.jay.springbootfeigntest.AutoInsert;
import io.jay.springbootfeigntest.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products",
        url = "https://dummyjson.com/products")
public interface ProductClient {

    @AutoInsert
    @GetMapping("/{id}")
    Product fetchProduct(@PathVariable int id);
}
