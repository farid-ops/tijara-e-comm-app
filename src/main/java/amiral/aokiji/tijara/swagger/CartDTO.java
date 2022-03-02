package amiral.aokiji.tijara.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "cart entity")
@NoArgsConstructor
@Getter
@Setter
public class CartDTO {

    @ApiModelProperty(value = "cart id", dataType = "java.lang.String")
    private String id;

    @ApiModelProperty(value = "user object ref", dataType = "aokiji.tijara.core.swagger.CartDTO")
    private UserDTO userDTO;
}
