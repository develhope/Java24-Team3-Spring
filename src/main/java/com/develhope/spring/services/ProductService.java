package com.develhope.spring.services;

import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.entities.ProductEntity;
import com.develhope.spring.exceptions.ProductNotFoundException;
import com.develhope.spring.mappers.ProductMapper;
import com.develhope.spring.daos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
//implementare metodi ricerca
    /**
     *
     * @param productDto
     * @return a new ProductDto
     */
    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity newProduct = this.productMapper.asEntity(productDto);
        this.productRepository.saveAndFlush(newProduct);
        return productMapper.asDto(newProduct);
    }

    /**
     * @return all products
     */
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = this.productRepository.findAll().stream().map(productMapper::asDto).toList();
        if (products.isEmpty()) {
            return new ArrayList<>();
        } else {
            return products;
        }
    }

    /**
     * @param id
     * @return a single Product
     */
    public ProductDto getSingleProduct(String id) {
        Optional<ProductEntity> productFound = this.productRepository.findById(id);
        if (productFound.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            return productMapper.asDto(productFound.get());
        }
    }

    /**
     * @param id
     * @param productDto
     * @return a product updated
     */
    public ProductDto updateProduct(String id, ProductDto productDto) {
        Optional<ProductEntity> productToUpdate = this.productRepository.findById(id);
        if (productToUpdate.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            productToUpdate.get().setName(productDto.getName());
            productToUpdate.get().setPrice(productDto.getPrice());
            productToUpdate.get().setIngredients(productDto.getIngredients());
            productToUpdate.get().setProductTypes(productDto.getProductTypes());
            return productMapper.asDto(this.productRepository.saveAndFlush(productToUpdate.get()));
        }
    }

    public void deleteProduct(String id) {
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
