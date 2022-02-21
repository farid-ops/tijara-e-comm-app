package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
}
