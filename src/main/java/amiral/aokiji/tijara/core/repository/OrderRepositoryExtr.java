package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.NewOrder;
import amiral.aokiji.tijara.core.model.OrderEntity;

import java.util.Optional;

public interface OrderRepositoryExtr {
    Optional<OrderEntity> insert(NewOrder newOrder);
}
