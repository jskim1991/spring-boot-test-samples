package io.jay.springbootfeigntest;

import io.jay.springbootfeigntest.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductClient client;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return client.fetchProduct(id);
    }
}
