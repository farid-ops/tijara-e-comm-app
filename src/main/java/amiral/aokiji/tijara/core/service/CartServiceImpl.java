package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.CartEntity;
import amiral.aokiji.tijara.core.model.ItemEntity;
import amiral.aokiji.tijara.core.repository.CartRepository;
import amiral.aokiji.tijara.core.repository.UserRepository;
import amiral.aokiji.tijara.exceptions.CustomerNotFoundException;
import amiral.aokiji.tijara.exceptions.GenericAlreadyExistsException;
import amiral.aokiji.tijara.swagger.ItemDTO;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ItemService itemService;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ItemService itemService) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.itemService = itemService;
    }

    @Override
    public List<ItemDTO> addCartItemByCustomerId(String customerId, @Valid ItemDTO itemDTO) {
        CartEntity cartEntity = this.getCartByCustomerId(customerId);

        long count = cartEntity.getItemEntities().stream().filter(
         i -> i.getProductEntity().getId().equals(UUID.fromString(itemDTO.getId()))
        ).count();

        if (count > 0)
            throw new GenericAlreadyExistsException(String.format("Item with id (%s) already exists, you can update ",itemDTO.getId()));
        cartEntity.getItemEntities().add(this.itemService.toEntity(itemDTO));

        return this.itemService.toModelList(cartRepository.save(cartEntity).getItemEntities());
    }

    @Override
    public List<ItemEntity> addOrReplaceItemByCustomerId(String customerId, @Valid ItemDTO itemDTO) {
        return null;
    }

    @Override
    public void deleteCart(String customerId) {
        CartEntity cartEntity = this.getCartByCustomerId(customerId);
        this.cartRepository.delete(cartEntity);
    }

    @Override
    public void deleteItemFromCart(String customerId, String itemId) {
        CartEntity cartEntity = this.getCartByCustomerId(customerId);
        List<ItemEntity> itemEntities = cartEntity.getItemEntities().stream()
                .filter(item -> !item.getProductEntity().getId().equals(UUID.fromString(itemId))).collect(Collectors.toList());
        cartEntity.setItemEntities(itemEntities);
        this.cartRepository.save(cartEntity);
    }

    @Override
    public CartEntity getCartByCustomerId(String customerId) {
        CartEntity cartEntity = this.cartRepository.findCartByCustomerId(UUID.fromString(customerId)).orElse(new CartEntity());
        if (Objects.isNull(cartEntity.getUserEntity())){
            cartEntity.setUserEntity(this.userRepository.findById(UUID.fromString(customerId))
                    .orElseThrow(()->new CustomerNotFoundException(String.format("-%s",customerId))));
        }
        return cartEntity;
    }

    @Override
    public List<ItemEntity> getCartItemByCustomerId(String customerId) {
        return null;
    }

    @Override
    public ItemEntity getCartItemByItemId(String customerId, String itemId) {
        return null;
    }
}
