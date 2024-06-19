package com.develhope.spring.services;

import com.develhope.spring.exceptions.InvalidProductException;
import com.develhope.spring.mappers.ProductTypeMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductEntity;
import com.develhope.spring.mappers.ProductMapper;
import com.develhope.spring.daos.ProductDao;
import com.develhope.spring.models.entities.ProductTypeEntity;
import com.develhope.spring.validators.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDao productDao;
    private final ProductMapper productMapper;
    private final ProductValidator productValidator;
    private final ProductTypeMapper productTypeMapper;
    private final ProductTypeDao productTypeDao;

    @Autowired
    private IdValidator idValidator;

    @Autowired
    public ProductService(ProductDao productDao, ProductMapper productMapper, ProductValidator productValidator, ProductTypeMapper productTypeMapper, ProductTypeDao productTypeDao) {
        this.productDao = productDao;
        this.productMapper = productMapper;
        this.productValidator = productValidator;
        this.productTypeMapper = productTypeMapper;
        this.productTypeDao = productTypeDao;
    }

    public List<ProductDto> createProducts(List<ProductDto> productDtos) throws Exception {
        List<ProductDto> productDtosWithId = new ArrayList<>();
        for (ProductDto p : productDtos) {
            idValidator.noId(p.getId());
            productValidator.validateProduct(p);
            ProductEntity newProduct = productMapper.toEntity(p);
            p.setId(newProduct.getId());
            productDtosWithId.add(p);
        }
        return productDtosWithId;


    }

    /**
     * @param productDto ProductDto
     * @return a new product
     */
    public ResponseModel createProduct(ProductDto productDto) {

        try {
            productValidator.validateProduct(productDto);
            ProductEntity newProduct = this.productMapper.toEntity(productDto);
            List<ProductTypeEntity> productTypeEntities = productTypeDao.saveAll(productTypeMapper.toEntityList(productDto.getProductTypes()));
            newProduct.setProductTypes(productTypeEntities);
            this.productDao.save(newProduct);
            return new ResponseModel(ResponseCode.B, productMapper.toDto(newProduct));
        } catch (InvalidProductException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }

    }

    /**
     * @return all products
     */
    public ResponseModel getAllProducts() {
        List<ProductDto> products = this.productDao.findAll().stream().map(productMapper::toDto).toList();
        if (products.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No products were found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, products);
        }
    }

    /**
     * @param id product id
     * @return a single Product
     */
    public ResponseModel getSingleProductById(String id) {
        Optional<ProductEntity> productFound = this.productDao.findById(id);
        if (productFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Product not found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, productMapper.toDto(productFound.get()));
        }
    }

    /**
     * @param name product name
     * @return all products with selected name
     */
    public ResponseModel getProductByName(String name) {
        List<ProductDto> productsFound = this.productDao.findProductByName(name).stream().map(productMapper::toDto).toList();
        if (productsFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No products were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, productsFound);
        }
    }

    /**
     * @param upperBoundPrice upper price range
     * @param lowerBoundPrice lower price range
     * @return all products in the selected price range
     */
    public ResponseModel getProductsByPriceRange(BigDecimal upperBoundPrice, BigDecimal lowerBoundPrice) {
        List<ProductDto> productsFound = this.productDao.findProductByPriceBetween(upperBoundPrice, lowerBoundPrice)
                .stream().map(productMapper::toDto).toList();
        if (productsFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No products were found with the selected parameters");
        } else {
            return new ResponseModel(ResponseCode.E, productsFound);
        }
    }

    /**
     * @param productType a productType used as parameter for the research
     * @return a list of products with the selected productType
     */
    public ResponseModel getProductByProductType(String productType) {
        ProductTypeEntity productTypeFound = this.productTypeDao.findByProductType(productType);
        List<ProductEntity> productsFound = this.productDao.findByProductTypesContains(productTypeFound);
        List<ProductDto> productFoundDto = productsFound.stream().map(productMapper::toDto).toList();
        if (productFoundDto.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No products were found with the selected parameter");
        } else {
            return new ResponseModel(ResponseCode.E, productFoundDto);
        }
    }

    /**
     * @param id             product id
     * @param productUpdates ProductDto
     * @return a product updated
     */
    public ResponseModel updateProduct(String id, ProductDto productUpdates) {
        Optional<ProductEntity> productToUpdate = this.productDao.findById(id);
        if (productToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Product not found with the selected ID");
        } else if (productUpdates != null) {
            ProductEntity productEntityUpdates = productMapper.toEntity(productUpdates);
            if (productUpdates.getName() != null) {
                productToUpdate.get().setName(productEntityUpdates.getName());
            }
            if (productUpdates.getPrice() != null) {
                productToUpdate.get().setPrice(productEntityUpdates.getPrice());
            }
            if (productUpdates.getIngredients() != null) {
                productToUpdate.get().setIngredients(productEntityUpdates.getIngredients());
            }
            if (productUpdates.getProductTypes() != null) {
                List<ProductTypeEntity> productTypeEntities = productTypeDao.saveAll(productTypeMapper.toEntityList(productUpdates.getProductTypes()));
                productToUpdate.get().setProductTypes(productTypeEntities);
            }
            return new ResponseModel(ResponseCode.G, this.productMapper.toDto(this.productDao.saveAndFlush(productToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    public ResponseModel updateProductsProductTypes(String id, List<ProductTypeDto> productTypeUpdates) {
        Optional<ProductEntity> productToUpdate = this.productDao.findById(id);
        if (productToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Product not found with the selected ID");
        } else if (productTypeUpdates != null) {
            List<ProductTypeEntity> productTypeEntityUpdates = productTypeMapper.toEntityList(productTypeUpdates);
            productToUpdate.get().setProductTypes(productTypeEntityUpdates);
            return new ResponseModel(ResponseCode.G, this.productMapper.toDto(this.productDao.saveAndFlush(productToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id product id
     * @return delete a single product
     */
    public ResponseModel deleteProductById(String id) {
        if (!productDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Product not found with the selected ID");
        } else {
            this.productDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("Product successfully deleted");
        }
    }

    /**
     * @return delete all products
     */
    public ResponseModel deleteAllProducts() {
        this.productDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All products have been deleted");
    }

}
