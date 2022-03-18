package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<OrderEntity, UUID> {

    @Query("select o from OrderEntity o join o.userEntity u where u.id = :customerId")
    Optional<OrderEntity> findCustomerId(@Param("customerId") UUID id);
}
