package amiral.aokiji.tijara.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "username is required")
    @Basic(optional = false)
    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "USER_STATUS")
    private String userstatus;

    @OneToOne(mappedBy = "userEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private CartEntity cartEntity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "user_id") ,
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<AddressEntity> addressEntities = Collections.emptyList();

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderEntity> orderEntities = Collections.emptyList();

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CardEntity> cardEntities = Collections.emptyList();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Collection<Role> roles = new ArrayList<>();
}
