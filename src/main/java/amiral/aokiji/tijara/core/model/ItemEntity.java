package amiral.aokiji.tijara.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ITEM")
@NoArgsConstructor
@Getter
@Setter
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "Quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private ProductEntity productEntity;

    @ManyToMany(mappedBy = "itemEntities", fetch = FetchType.LAZY)
    private List<CartEntity> cartEntities = Collections.emptyList();

    @ManyToMany(mappedBy = "itemEntities", fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntities = Collections.emptyList();
}
