package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.ItemEntity;
import amiral.aokiji.tijara.swagger.ItemDTO;

import java.util.List;

public interface ItemService {

    ItemEntity toEntity(ItemDTO itemDTO);

    List<ItemEntity> toEntityList(List<ItemDTO> itemDTOS);

    ItemDTO toModel(ItemEntity itemEntity);

    List<ItemDTO> toModelList(List<ItemEntity> itemEntities);
}
