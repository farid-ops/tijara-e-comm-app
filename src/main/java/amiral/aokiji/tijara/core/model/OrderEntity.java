package amiral.aokiji.tijara.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ORDER_ENTITY")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    private AddressEntity addressEntity;

    @OneToOne
    @JoinColumn(name = "SHIPMENT_ID", referencedColumnName = "ID")
    private ShipmentEntity shipmentEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PAYMENT_ID", referencedColumnName = "ID")
    private PaymentEntity paymentEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID", referencedColumnName = "ID")
    private CardEntity cardEntity;

    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "ORDER_ITEM",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<ItemEntity> itemEntities = Collections.emptyList();

    @OneToOne(mappedBy = "orderEntity")
    private AuthorizationEntity authorizationEntity;
}
