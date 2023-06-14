package com.convera.postgress.data.api.repository;

import com.convera.postgress.data.api.repository.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Product repository for CRUD operation.
 *
 * @author Vikram Sahl
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
