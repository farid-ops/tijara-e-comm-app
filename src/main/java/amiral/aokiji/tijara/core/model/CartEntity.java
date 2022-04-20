package amiral.aokiji.tijara.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "CART")
@NoArgsConstructor
@Getter
@Setter
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserEntity userEntity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CART_ID_ITEM_ID",
            joinColumns = @JoinColumn(name = "CART_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<ItemEntity> itemEntities = Collections.emptyList();
}
