package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.CartEntity;
import amiral.aokiji.tijara.core.model.ItemEntity;
import amiral.aokiji.tijara.core.model.OrderEntity;
import amiral.aokiji.tijara.core.model.StatusEnum;
import amiral.aokiji.tijara.swagger.OrderDTO;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Transactional
public class OrderRepositoryExtrImpl implements OrderRepositoryExtr {

    @PersistenceContext
    private EntityManager entityManager;

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final OrderItemEntityRepository orderItemEntityRepository;
    private final OrderRepository orderRepository;

    public OrderRepositoryExtrImpl(EntityManager entityManager,
                                   ItemRepository itemRepository,
                                   CartRepository cartRepository,
                                   OrderItemEntityRepository orderItemEntityRepository,
                                   OrderRepository orderRepository){
        this.entityManager = entityManager;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.orderItemEntityRepository = orderItemEntityRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<OrderEntity> insert(OrderDTO orderDTO) {

        Iterable<ItemEntity> itemdb = this.itemRepository.findByCustomerId(orderDTO.getUserDTO().getId());
        List<ItemEntity> items = StreamSupport.stream(itemdb.spliterator(), false).collect(Collectors.toList());
        if (items.size() < 1){
            throw new ResourceNotFoundException("item not found in customer's (ID:%s) cart "+orderDTO.getUserDTO().getId());
        }
        BigDecimal total = BigDecimal.ZERO;
        for (ItemEntity item: items){
            total = BigDecimal.valueOf(item.getQuantity()).multiply(item.getPrice()).add(total);
        }
        Timestamp orderDate = Timestamp.from(Instant.now());
        this.entityManager.createNativeQuery(
                "insert into tijara.orders(customer_id, address_id, card_id, order_date, total, status)values(?,?,?,?,?,?)"
        )
                .setParameter(1, orderDTO.getUserDTO().getId())
                .setParameter(2, orderDTO.getAddressDTO().getId())
                .setParameter(3, orderDTO.getCardDTO().getId())
                .setParameter(4, orderDate)
                .setParameter(5, total)
                .setParameter(6, StatusEnum.CREATED)
                .executeUpdate();

        Optional<CartEntity> cartEntity = this.cartRepository.findCartByCustomerId(UUID.fromString(orderDTO.getUserDTO().getId()));
        CartEntity cart = cartEntity.orElseThrow(()->new ResourceNotFoundException("Cart not found for given customer (ID: %s)"+orderDTO.getUserDTO().getId()));
//        this.itemRepository.deleteCartItemJoinById(cart.get);
        return Optional.empty();
    }
}
