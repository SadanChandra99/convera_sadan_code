package com.convera.postgress.data.api.web;

//import com.convera.postgress.data.api.constants.ProductStatus;
//import com.convera.postgress.data.api.exception.DataNotFoundException;
//import com.convera.postgress.data.api.repository.ProductRepository;
//import com.convera.postgress.data.api.repository.model.Product;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////@WebMvcTest(controllers = ProductsController.class)
//class ProductsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @InjectMocks
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private ProductRepository productRepository;
//
//    @Test
//    void getProduct_success() throws Exception {
//        Product product = new Product(0L, "first", "first product description", ProductStatus.AVAILABLE);
//        when(productRepository.findById(any()))
//                .thenReturn(Optional.of(product));
//        this.mockMvc.perform(get("/convera/product/0"))
//            .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.id", Matchers.is((int)product.getId())))
//                .andExpect(jsonPath("$.data.name", Matchers.is(product.getName())))
//                .andExpect(jsonPath("$.data.description", Matchers.is(product.getDescription())))
//                .andExpect(jsonPath("$.data.product_status", Matchers.is(product.getProductStatus().toString().toLowerCase())));
//    }
//
//    @Test
//    void getProduct_notFound() throws Exception {
//        when(productRepository.findById(any()))
//                .thenReturn(Optional.empty());
//        this.mockMvc.perform(get("/convera/product/0"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void saveProduct_success() throws Exception {
//        Product product = new Product(0L, "first", "first product description", ProductStatus.AVAILABLE);
//        when(productRepository.save(any())).thenReturn(product);
//        this.mockMvc.perform(
//                post("/convera/product")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(product))
//            )
//            .andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.data.name", Matchers.is(product.getName())))
//            .andExpect(jsonPath("$.data.description", Matchers.is(product.getDescription())))
//            .andExpect(jsonPath("$.data.product_status", Matchers.is(product.getProductStatus().toString().toLowerCase())));;
//    }
//
//    @Test
//    void saveProduct_ThrowBadRequest() throws Exception {
//        Product product = new Product(0L, "first", "first product description", ProductStatus.AVAILABLE);
//        when(productRepository.save(any())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
//        this.mockMvc.perform(
//                post("/convera/product")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(product))
//            )
//            .andDo(print())
//            .andExpect(status().isBadRequest())
//            .andExpect(jsonPath("$.metadata.statusCode",Matchers.is(400)))
//            .andExpect(jsonPath("$.metadata.statusDescription", Matchers.is("Bad request")));
//    }
//
//    @Test
//    void saveProduct_ThrowException() throws Exception {
//        Product product = new Product(0L, "first", "first product description", ProductStatus.AVAILABLE);
//        when(productRepository.save(any())).thenThrow(new DataNotFoundException("db error"));
//        this.mockMvc.perform(
//                post("/convera/product")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(product))
//            )
//            .andDo(print())
//            .andExpect(status().isInternalServerError())
//            .andExpect(jsonPath("$.metadata.statusCode",Matchers.is(500)))
//            .andExpect(jsonPath("$.metadata.statusDescription", Matchers.is("Internal Server Error")));
//    }
//}