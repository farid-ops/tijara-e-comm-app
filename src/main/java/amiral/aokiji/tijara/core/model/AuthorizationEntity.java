package amiral.aokiji.tijara.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "AUTHORIZATION_ENTITY")
@NoArgsConstructor
@Getter
public class AuthorizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "AUTHORIZED")
    private boolean authorized;

    @Column(name = "TIME")
    private Timestamp time;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "ERROR")
    private String error;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ENTITY_ID", referencedColumnName = "ID")
    private OrderEntity orderEntity;
}
