package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.CartEntity;
import amiral.aokiji.tijara.core.model.ItemEntity;
import amiral.aokiji.tijara.core.repository.CartRepository;
import amiral.aokiji.tijara.core.repository.UserRepository;
import amiral.aokiji.tijara.exceptions.CustomerNotFoundException;
import amiral.aokiji.tijara.exceptions.GenericAlreadyExistsException;
import amiral.aokiji.tijara.exceptions.ItemNotFoundException;
import amiral.aokiji.tijara.swagger.ItemDTO;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.springframework.objenesis.instantiator.util.UnsafeUtils.getUnsafe;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ItemService itemService;

    public CartServiceImpl(CartRepository cartRepository,
                           UserRepository userRepository,
                           ItemService itemService) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.itemService = itemService;
    }

    @Override
    public List<ItemDTO> addCartItemByCustomerId(String customerId, @Valid ItemDTO itemDTO) {
        CartEntity entity = this.getCartByCustomerId(customerId);
        long count = entity.getItemEntities().stream().filter(
                identifiant->identifiant.getProductEntity().getId().equals(UUID.fromString(itemDTO.getId()))
        ).count();
        if (count > 0){
            throw new GenericAlreadyExistsException("Item with Id (%s) already exist. you can update it"+itemDTO.getId());
        }
        entity.getItemEntities().add(this.itemService.mapItemDTOToItem(itemDTO));
        return this.itemService.mapItemEntityToItemDTOS(this.cartRepository.save(entity).getItemEntities());
    }

    @Override
    public List<ItemDTO> addOrReplaceItemByCustomerId(String customerId, @Valid ItemDTO itemDTO) {
        CartEntity entity = this.getCartByCustomerId(customerId);
        List<ItemEntity> entities = Objects.nonNull(entity.getItemEntities()) ? entity.getItemEntities() : Collections.emptyList();

        AtomicBoolean itemsExist = new AtomicBoolean(false);

        entities.forEach(item->{
            if(item.getProductEntity().getId().equals(UUID.fromString(itemDTO.getId()))){
                item.setQuantity(itemDTO.getQuantity());
                item.setPrice(itemDTO.getPrice());
                itemsExist.set(true);
            }
        });

        if (itemsExist.get()){
            entities.add(itemService.mapItemDTOToItem(itemDTO));
        }

        return itemService.mapItemEntityToItemDTOS(this.cartRepository.save(entity).getItemEntities());
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
                .filter(i->!i.getProductEntity().getId().equals(UUID.fromString(itemId))).collect(Collectors.toList());
        cartEntity.setItemEntities(itemEntities);
        this.cartRepository.save(cartEntity);
    }

    @Override
    public CartEntity getCartByCustomerId(String customerId) {
        CartEntity cartEntity = this.cartRepository.findCartByCustomerId(UUID.fromString(customerId))
                .orElse(new CartEntity());
        if (Objects.isNull(cartEntity.getUserEntity()))
            cartEntity.setUserEntity(this.userRepository.findById(UUID.fromString(customerId))
            .orElseThrow(()-> new CustomerNotFoundException(String.format(" ~ %s", customerId))));
        return cartEntity;
    }

    @Override
    public List<ItemDTO> getCartItemByCustomerId(String customerId) {
        CartEntity cartEntity = this.getCartByCustomerId(customerId);
        return itemService.mapItemEntityToItemDTOS(cartEntity.getItemEntities());
    }

    @Override
    public ItemDTO getCartItemByItemId(String customerId, String itemId) {
        CartEntity entity = this.getCartByCustomerId(customerId);
        AtomicReference<ItemEntity> items = new AtomicReference<>();
        entity.getItemEntities().forEach(
                i->{
                    if (i.getProductEntity().getId().equals(UUID.fromString(itemId))){
                        items.set(i);
                    }
                }

        );
        if (Objects.isNull(items.get())){
            getUnsafe().throwException(new ItemNotFoundException(String.format(" - %s", itemId)));
        }
        return this.itemService.mapItemEntityToItemDTO(items.get());
    }
}
