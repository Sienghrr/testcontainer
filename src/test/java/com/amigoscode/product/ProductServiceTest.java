package com.amigoscode.product;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest /*only test jpa component*/
@AutoConfigureTestDatabase(
        replace = AutoConfigureTestDatabase.Replace.NONE // for use our own db , not the autoConfigure
)
@Import({
        ProductService.class,
        org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration.class
}) // for autowired
@Testcontainers // for using container
class ProductServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgre =
            new PostgreSQLContainer<>(
                    DockerImageName.parse("postgres:17-alpine")
            ).withDatabaseName("test").withUsername("sieng").withPassword("123")
            ;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService underTest;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    @Disabled
    void canGetAllProducts() {
        //given
        Product product = new Product(
                UUID.randomUUID(),
                "sieng","akdkdjkfdkfdklkj", BigDecimal.TEN,"http://sieng.com",20 );
        Product allProduct = productRepository.save(product);

        //when
        List<ProductResponse> allProducts = underTest.getAllProducts();

        //then
        ProductResponse expected = underTest.mapToResponse().apply(product);
        assertThat(allProducts)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("createdAt","updatedAt")
                .containsOnly(expected);
    }




    @Test
    void getProductById() {
        //given
        //when
        //then
    }

    @Test
    void deleteProductById() {
        //given
        //when
        //then
    }

    @Test
    void saveNewProduct() {
        //given
        //when
        //then
    }

    @Test
    void updateProduct() {
        //given
        //when
        //then
    }
}