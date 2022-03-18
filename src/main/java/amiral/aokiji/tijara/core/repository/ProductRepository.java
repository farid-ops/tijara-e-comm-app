package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {

}
