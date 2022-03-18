package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.ItemEntity;
import amiral.aokiji.tijara.core.model.ProductEntity;
import amiral.aokiji.tijara.swagger.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public ItemEntity toEntity(ItemDTO itemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setProductEntity(new ProductEntity().setId(UUID.fromString(itemDTO.getId())))
                .setPrice(itemDTO.getPrice()).setQuantity(itemDTO.getQuantity());
        return itemEntity;
    }

    @Override
    public List<ItemEntity> toEntityList(List<ItemDTO> itemDTOS) {
        if (Objects.isNull(itemDTOS))
            return Collections.emptyList();
        return itemDTOS.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public ItemDTO toModel(ItemEntity itemEntity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemEntity.getProductEntity().getId().toString())
                .setPrice(itemEntity.getPrice()).setQuantity(itemEntity.getQuantity());
        return itemDTO;
    }

    @Override
    public List<ItemDTO> toModelList(List<ItemEntity> itemEntities) {
        if (Objects.isNull(itemEntities))
            return Collections.emptyList();
        return itemEntities.stream().map(this::toModel).collect(Collectors.toList());
    }
}
