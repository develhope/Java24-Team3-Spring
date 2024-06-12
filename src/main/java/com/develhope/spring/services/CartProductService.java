package com.develhope.spring.services;

import com.develhope.spring.daos.CartProductDao;
import com.develhope.spring.exceptions.InvalidCartProductException;
import com.develhope.spring.mappers.CartProductMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.CartProductDto;
import com.develhope.spring.models.entities.CartProductEntity;
import com.develhope.spring.validators.CartProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartProductService {

    private final CartProductDao cartProductDao;
    private final CartProductMapper cartProductMapper;
    private final CartProductValidator cartProductValidator;

    @Autowired
    public CartProductService(CartProductDao cartProductDao, CartProductMapper cartProductMapper, CartProductValidator cartProductValidator) {
        this.cartProductDao = cartProductDao;
        this.cartProductMapper = cartProductMapper;
        this.cartProductValidator = cartProductValidator;
    }

    /**
     * @param cartProductDto CartProductDto
     * @return a new cartProduct
     */
    public ResponseModel createCartProduct(CartProductDto cartProductDto) {

        try {
            cartProductValidator.validateCartProduct(cartProductDto);
            CartProductEntity newCartProduct = this.cartProductMapper.toEntity(cartProductDto);
            this.cartProductDao.saveAndFlush(newCartProduct);
            return new ResponseModel(ResponseCode.B, this.cartProductMapper.toDTO(newCartProduct));
        } catch (InvalidCartProductException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    /**
     * @param id cartProduct id
     * @return a single cartProduct with the selected ID
     */
    public ResponseModel getCartProductById(String id) {
        Optional<CartProductEntity> cartProductFound = this.cartProductDao.findById(id);
        if (cartProductFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("CartProduct not found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, this.cartProductMapper.toDTO(cartProductFound.get()));
        }
    }

    /**
     * @return List of all cartProducts
     */
    public ResponseModel getAllCartProducts() {
        List<CartProductEntity> cartProductsFound = this.cartProductDao.findAll();
        if (cartProductsFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No cartProducts were found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, cartProductsFound);
        }
    }

    /**
     * @param id                 cartProduct ID
     * @param cartProductUpdates updates for a cartProduct
     * @return a cartProduct updated
     */
    public ResponseModel updateCartProduct(String id, CartProductDto cartProductUpdates) {
        Optional<CartProductEntity> cartProductToUpdate = this.cartProductDao.findById(id);
        if (cartProductToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("CartProduct not found with the selected ID");
        } else if (cartProductUpdates != null) {
            CartProductEntity cartProductEntityUpdates = this.cartProductMapper.toEntity(cartProductUpdates);
            if (cartProductUpdates.getQuantity() > 0) {
                cartProductToUpdate.get().setQuantity(cartProductEntityUpdates.getQuantity());
            }
            if (cartProductUpdates.getProductId() != null) {
                cartProductToUpdate.get().setProduct(cartProductEntityUpdates.getProduct());
            }
            if (cartProductUpdates.getCartId() != null) {
                cartProductToUpdate.get().setCart(cartProductEntityUpdates.getCart());
            }
            return new ResponseModel(ResponseCode.G, this.cartProductMapper.toDTO(this.cartProductDao.saveAndFlush(cartProductToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id
     * @Effect: delete a cartProduct
     */
    public ResponseModel deleteCartProduct(String id) {
        if (!this.cartProductDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("CartProduct not found with the selected iD");
        } else {
            this.cartProductDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("CartProduct successfully deleted");
        }
    }

    /**
     * @Effect: deleted all cartProducts
     */
    public ResponseModel deleteAllCartProducts() {
        this.cartProductDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All CartProducts have been deleted");
    }
}
