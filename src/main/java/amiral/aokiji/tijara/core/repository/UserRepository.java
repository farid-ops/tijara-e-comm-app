package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
