package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.OrderEntity;
import amiral.aokiji.tijara.core.repository.OrderRepository;
import amiral.aokiji.tijara.swagger.OrderDTO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<OrderEntity> addOrder(@Valid OrderDTO order) {
        if (Strings.isEmpty(order.getUserDTO().getId()))
            throw new ResourceNotFoundException("Invalid customer ID : ");
        if (Objects.isNull(order.getAddressDTO()) || Strings.isEmpty(order.getAddressDTO().getId()))
            throw new ResourceNotFoundException("Invalid address ID : ");
        if (Objects.isNull(order.getCardDTO()) || Strings.isEmpty(order.getCardDTO().getId()))
            throw new ResourceNotFoundException("Invalid card ID : ");
        return Optional.empty();
    }

    @Override
    public Optional<OrderEntity> getOrderByCustomerId(@NotNull @Valid String customerId) {
        return this.orderRepository.findCustomerId(UUID.fromString(customerId));
    }

    @Override
    public Optional<OrderEntity> getOrderById(String orderId) {
        return this.orderRepository.findById(UUID.fromString(orderId));
    }
}
