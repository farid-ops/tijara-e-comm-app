package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.ItemEntity;
import amiral.aokiji.tijara.swagger.ItemDTO;

import java.util.List;

public interface ItemService {

    ItemEntity mapItemDTOToItem(ItemDTO itemDTO);

    List<ItemEntity> mapItemDTOSToItems(List<ItemDTO> itemDTOS);

    ItemDTO mapItemEntityToItemDTO(ItemEntity itemEntity);

    List<ItemDTO> mapItemEntityToItemDTOS(List<ItemEntity> itemEntities);
}
