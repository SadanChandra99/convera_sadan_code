package com.convera.postgress.data.api.repo;

import com.convera.postgress.data.api.constants.ProductStatus;
import com.convera.postgress.data.api.repository.ProductRepository;
import com.convera.postgress.data.api.repository.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@DataJpaTest
class TestEntity {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private ProductRepository repository;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(em);
    }

    @Test
    void verifyProductPersist() {
        Product prod = new Product(1L, "ProductName", "A product description", ProductStatus.AVAILABLE);

        em.persist(prod);

        Optional<Product> product = repository.findById(1L);
        Assertions.assertEquals(Boolean.TRUE, product.isPresent());
        Assertions.assertEquals(1L, product.get().getId());


    }


}
