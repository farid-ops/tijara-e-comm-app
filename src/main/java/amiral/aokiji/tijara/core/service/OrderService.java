package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.OrderEntity;
import amiral.aokiji.tijara.swagger.OrderDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface OrderService {

    Optional<OrderEntity> addOrder(@Valid OrderDTO order);

    Optional<OrderEntity> getOrderByCustomerId(@NotNull @Valid String customerId);

    Optional<OrderEntity> getOrderById(String orderId);
}
