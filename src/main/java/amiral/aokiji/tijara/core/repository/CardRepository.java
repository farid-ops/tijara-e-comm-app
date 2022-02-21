package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.CardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardRepository extends CrudRepository<CardEntity, UUID> {
}
