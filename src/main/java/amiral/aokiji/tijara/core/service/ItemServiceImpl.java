package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.ItemEntity;
import amiral.aokiji.tijara.swagger.ItemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ItemServiceImpl implements ItemService {


    /*
    * Transforme une entity Item en un POJO ItemDTO.
    * */
    @Override
    public ItemEntity mapItemDTOToItem(ItemDTO itemDTO) {
        ModelMapper mapper = new ModelMapper();
        ItemEntity item = mapper.map(itemDTO, ItemEntity.class);
        return item;
    }

    @Override
    public List<ItemEntity> mapItemDTOSToItems(List<ItemDTO> itemDTOS) {
        if (Objects.isNull(itemDTOS))
            return Collections.emptyList();
        return itemDTOS.stream().map(this::mapItemDTOToItem).collect(Collectors.toList());
    }

    @Override
    public ItemDTO mapItemEntityToItemDTO(ItemEntity itemEntity) {
        ModelMapper mapper = new ModelMapper();
        ItemDTO itemDTO = mapper.map(itemEntity, ItemDTO.class);
        return itemDTO;
    }

    @Override
    public List<ItemDTO> mapItemEntityToItemDTOS(List<ItemEntity> itemEntities) {
        if (Objects.isNull(itemEntities))
            return Collections.emptyList();
        return itemEntities.stream().map(this::mapItemEntityToItemDTO).collect(Collectors.toList());
    }
}
