package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.CartEntity;
import amiral.aokiji.tijara.swagger.ItemDTO;

import javax.validation.Valid;
import java.util.List;

public interface CartService {

    List<ItemDTO> addCartItemByCustomerId(String customerId, @Valid ItemDTO itemDTO);

    List<ItemDTO> addOrReplaceItemByCustomerId(String customerId, @Valid ItemDTO itemDTO);

    void deleteCart(String customerId);

    void deleteItemFromCart(String customerId, String itemId);

    CartEntity getCartByCustomerId(String customerId);

    List<ItemDTO> getCartItemByCustomerId(String customerId);

    ItemDTO getCartItemByItemId(String customerId, String itemId);
}
