package io.jay.springbootfeigntest;

import io.jay.springbootfeigntest.client.ProductClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AutoInsertAspectTests {

    ProductRepository repository;

    ProductClient client;

    @BeforeEach
    void setup() {
        client = mock(ProductClient.class);
        repository = mock(ProductRepository.class);
    }

    @Test
    void test_aop_insertsSuccessfully() {
        Product product = Product.builder()
                .id(1)
                .title("iPhone 99")
                .build();
        when(client.fetchProduct(1))
                .thenReturn(product);

        AspectJProxyFactory factory = new AspectJProxyFactory(client);
        AutoInsertAspect aspect = new AutoInsertAspect(repository);
        factory.addAspect(aspect);
        ProductClient proxy = factory.getProxy();


        proxy.fetchProduct(1);


        verify(repository).save(product);
    }
}
