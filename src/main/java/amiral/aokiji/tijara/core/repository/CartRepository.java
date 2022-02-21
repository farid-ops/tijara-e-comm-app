package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.CartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends CrudRepository<CartEntity, UUID> {

    @Query("select c from CardEntity c join c.userEntity u where u.id = :customerId")
    Optional<CartEntity> findCustomerId(@Param("customerId") UUID id);
}
