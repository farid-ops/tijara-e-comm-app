package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TagRepository extends CrudRepository<TagEntity, UUID> {
}
