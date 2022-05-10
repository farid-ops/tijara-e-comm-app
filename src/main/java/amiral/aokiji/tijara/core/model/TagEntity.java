package amiral.aokiji.tijara.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "TAG_ENTITY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    public TagEntity(@NotNull(message = "Product name is required") String name) {
        this.name = name;
    }

    @NotNull(message = "Product name is required")
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
}
