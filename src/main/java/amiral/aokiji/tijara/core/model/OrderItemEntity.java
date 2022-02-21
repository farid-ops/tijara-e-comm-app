package amiral.aokiji.tijara.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ORDER_ITEM_ENTITY")
@NoArgsConstructor
@Getter
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "ORDER_ID")
    private UUID orderId;

    @Column(name = "ITEM_ID")
    private UUID itemId;
}
