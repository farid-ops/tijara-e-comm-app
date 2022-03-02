package amiral.aokiji.tijara.core.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "CARD")
@Getter
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "EXPIRES")
    private String expires;

    @Column(name = "CVV")
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "cardEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderEntity> orderEntities = Collections.emptyList();

    public CardEntity setNumber(String number){
        this.number = number;
        return this;
    }

    public CardEntity setExpires(String expires){
        this.expires = expires;
        return this;
    }

    public CardEntity setCvv(String cvv){
        this.cvv = cvv;
        return this;
    }

    public CardEntity setUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
        return this;
    }

    public CardEntity setOrderEntity(List<OrderEntity> orderEntities){
        this.orderEntities = orderEntities;
        return this;
    }
}
