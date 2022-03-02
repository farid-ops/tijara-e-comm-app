package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.CartEntity;
import amiral.aokiji.tijara.core.model.ItemEntity;

import javax.validation.Valid;
import java.util.List;

public interface CartService {

    List<ItemEntity> addCartItemByCustomerId(String customerId, @Valid ItemEntity itemEntity);

    List<ItemEntity> addOrReplaceItemByCustomerId(String customerId, @Valid ItemEntity itemEntity);

    void deleteCart(String customerId);

    void deleteItemFromCart(String customerId, String itemId);

    CartEntity getCartByCustomerId(String customerId);

    List<ItemEntity> getCartItemByCustomerId(String customerId);

    ItemEntity getCartItemByItemId(String customerId, String itemId);
}
