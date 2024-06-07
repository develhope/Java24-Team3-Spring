package com.develhope.spring.services;

import com.develhope.spring.daos.CartDao;
import com.develhope.spring.exceptions.InvalidCartException;
import com.develhope.spring.mappers.CartMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.CartDto;
import com.develhope.spring.models.entities.CartEntity;
import com.develhope.spring.validators.CartValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartDao cartDao;
    private final CartMapper cartMapper;
    private final CartValidator cartValidator;

    @Autowired
    public CartService(CartDao cartDao, CartMapper cartMapper, CartValidator cartValidator) {
        this.cartDao = cartDao;
        this.cartMapper = cartMapper;
        this.cartValidator = cartValidator;
    }

    /**
     * @param cartDto CartDto
     * @return a new cart
     */
    public ResponseModel createCart(CartDto cartDto) {
        try {
            cartValidator.validateCart(cartDto);
            CartEntity newCart = this.cartMapper.toEntity(cartDto);
            this.cartDao.saveAndFlush(newCart);
            return new ResponseModel(ResponseCode.B, this.cartMapper.toDTO(newCart));
        } catch (InvalidCartException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    /**
     * @param id cart ID
     * @return a single cart with the selected ID
     */
    public ResponseModel getCartById(String id) {
        Optional<CartEntity> cartFound = this.cartDao.findById(id);
        if (cartFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Cart not found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, this.cartMapper.toDTO(cartFound.get()));
        }
    }

    public ResponseModel getAllCarts() {
        List<CartEntity> cartsFound = this.cartDao.findAll();
        if (cartsFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No carts found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, cartsFound);
        }
    }

    /**
     * @param id cart id
     * @param cartUpdates updates for cart
     * @return an updated cart
     */
    public ResponseModel updateCartsCartProducts(String id, CartDto cartUpdates) {
        Optional<CartEntity> cartToUpdate = this.cartDao.findById(id);
        if (cartToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Cart not found with the selected ID");
        } else if (cartUpdates != null) {
            CartEntity cartEntityUpdates = this.cartMapper.toEntity(cartUpdates);
            if (cartUpdates.getCartProducts() != null) {
                cartToUpdate.get().setCartProducts(cartEntityUpdates.getCartProducts());
                return new ResponseModel(ResponseCode.G, this.cartMapper.toDTO(this.cartDao.saveAndFlush(cartToUpdate.get())));
            }
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param id cart id
     * @Effect: delete a cart by ID
     */
    public ResponseModel deleteCart(String id) {
        if(!this.cartDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("Cart not found with the selected ID");
        } else {
            this.cartDao.deleteById(id);
            return new ResponseModel(ResponseCode.H).addMessageDetails("Cart successfully deleted");
        }
    }

    /**
     * @Effect: delete all carts
     */
    public ResponseModel deleteAllCarts() {
        this.cartDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All carts have been deleted");
    }

}
