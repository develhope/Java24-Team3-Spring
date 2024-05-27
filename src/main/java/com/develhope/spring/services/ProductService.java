package com.develhope.spring.services;

import com.develhope.spring.dtos.ProductDTO;
import com.develhope.spring.entities.ProductEntity;
import com.develhope.spring.exceptions.ProductNotFoundException;
import com.develhope.spring.mappers.ProductMapper;
import com.develhope.spring.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity newProduct = this.productMapper.asEntity(productDTO);
        this.productRepository.saveAndFlush(newProduct);
        return productMapper.asDto(newProduct);
    }

    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> products = this.productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            return products;
        }
    }

    public ProductEntity getSingleProduct(UUID id) {
        Optional<ProductEntity> productFound = this.productRepository.findById(id);
        if (productFound.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            return productFound.get();
        }
    }

    public ProductDTO updateProduct(UUID id, ProductDTO productDTO) {
        Optional<ProductEntity> productToUpdate = this.productRepository.findById(id);
        if (productToUpdate.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            productToUpdate.get().setName(productDTO.getName());
            productToUpdate.get().setPrice(productDTO.getPrice());
            productToUpdate.get().setIngredients(productDTO.getIngredients());
            return productMapper.asDto(this.productRepository.saveAndFlush(productToUpdate.get()));
        }
    }

    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        } else {
            this.productRepository.deleteById(id);
        }
    }

    public void deleteAllProducts() {
        this.productRepository.deleteAll();
    }

}
