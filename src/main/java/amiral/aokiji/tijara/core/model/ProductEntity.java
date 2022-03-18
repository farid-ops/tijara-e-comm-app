package amiral.aokiji.tijara.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT_ENTITY")
@NoArgsConstructor
@Getter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private UUID id;

    @NotNull(message = "Product name is required")
    @Basic(optional = false)
    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "COUNT")
    private int count;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
    private List<ItemEntity> itemEntities = Collections.emptyList();

    @OneToMany
    @JoinTable(
            name = "PRODUCT_TAG",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private List<TagEntity> tagEntities = Collections.emptyList();

    public ProductEntity setId(UUID id){
        this.id = id;
        return this;
    }

    public ProductEntity setName(String name){
        this.name = name;
        return this;
    }

    public ProductEntity setDescription(String description){
        this.description = description;
        return this;
    }

    public ProductEntity setPrice(BigDecimal price){
        this.price = price;
        return this;
    }

    public ProductEntity setCount(int count){
        this.count = count;
        return this;
    }

    public ProductEntity setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
        return this;
    }

    public ProductEntity setTagEntity(List<TagEntity> tagEntities){
        this.tagEntities = tagEntities;
        return this;
    }

}
