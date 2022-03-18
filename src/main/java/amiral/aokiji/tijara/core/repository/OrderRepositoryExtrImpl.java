package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.NewOrder;
import amiral.aokiji.tijara.core.model.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class OrderRepositoryExtrImpl implements OrderRepositoryExtr {

    @PersistenceContext
    private EntityManager entityManager;
    private ItemRepository itemRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    @Override
    public Optional<OrderEntity> insert(NewOrder newOrder) {
        return Optional.empty();
    }
}
