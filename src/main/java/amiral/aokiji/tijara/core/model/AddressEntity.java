package amiral.aokiji.tijara.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "RESIDENCY")
    private String residency;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PINCODE")
    private String pincode;

    @OneToMany(mappedBy = "addressEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderEntity> orderEntities = Collections.emptyList();

    public AddressEntity setNumber(String number){
        this.number = number;
        return this;
    }

    public AddressEntity setResidency(String residency){
        this.residency = residency;
        return this;
    }

    public AddressEntity setStreet(String street){
        this.street = street;
        return this;
    }

    public AddressEntity setCity(String city){
        this.city = city;
        return this;
    }

    public AddressEntity setState(String state){
        this.state = state;
        return this;
    }

    public AddressEntity setCountry(String country){
        this.country = country;
        return this;
    }

    public AddressEntity setPincode(String pincode){
        this.pincode = pincode;
        return this;
    }

    public AddressEntity setOrderEntity(List<OrderEntity> orderEntities){
        this.orderEntities = orderEntities;
        return this;
    }

}
