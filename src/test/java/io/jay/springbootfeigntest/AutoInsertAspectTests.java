package io.jay.springbootfeigntest;

import io.jay.springbootfeigntest.client.ProductClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
public class AutoInsertAspectTests {

    @SpyBean
    ProductRepository repository;

    @Mock
    ProductClient client;

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
