package io.jay.springbootfeigntest;

import io.jay.springbootfeigntest.client.ProductClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignClientFactoryBean;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductClientTests extends FeignClientFactoryBean {

    @Autowired
    FeignContext context;

    @Value("${local.server.port}")
    private int port = 0;

    public ProductClientTests() {
        setName("tests");
        setContextId("test");
    }

    public ProductClient testClient() {
        setType(this.getClass());
        return feign(context).target(ProductClient.class, "http://localhost:" + port);
    }

    @Test
    void stub_productClient_fetchProduct_returns_product() {
        Product testProduct = testClient().fetchProduct(1);


        assertThat(testProduct.getTitle(), equalTo("iPhone 99"));
    }

    @Configuration
    @EnableAutoConfiguration
    @RestController
    protected static class App implements ProductClient {
        @Override
        public Product fetchProduct(int id) {
            return Product.builder()
                    .id(1)
                    .title("iPhone 99")
                    .description("99th iPhone")
                    .price(999)
                    .discountPercentage(0)
                    .rating(4.69f)
                    .stock(0)
                    .brand("Apple")
                    .category("smartphones")
                    .build();
        }
    }
}
