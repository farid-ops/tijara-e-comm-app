package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.NewOrder;
import amiral.aokiji.tijara.core.model.OrderEntity;
import amiral.aokiji.tijara.swagger.OrderDTO;

import java.util.Optional;

public interface OrderRepositoryExtr {
    Optional<OrderEntity> insert(OrderDTO orderDTO);
}
