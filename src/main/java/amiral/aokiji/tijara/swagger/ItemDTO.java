package amiral.aokiji.tijara.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "Item entity")
public class ItemDTO {

    @ApiModelProperty(value = "item id", dataType = "java.lang.String")
    private String id;

    @ApiModelProperty(value = "price", dataType = "java.math.BigDecimal")
    private BigDecimal price;

    @ApiModelProperty(value = "quantity", dataType = "int")
    private int quantity;

}
