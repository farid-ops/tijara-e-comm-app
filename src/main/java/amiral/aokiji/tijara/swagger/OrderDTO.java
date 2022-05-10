package amiral.aokiji.tijara.swagger;

import amiral.aokiji.tijara.core.model.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "order entity Dto")
public class OrderDTO {

    @ApiModelProperty(value = "id", dataType = "java.lang.String")
    private UUID id;

    @ApiModelProperty(value = "total", dataType = "java.lang.String")
    private BigDecimal total;

    @ApiModelProperty(value = "enum", dataType = "java.lang.String")
    private StatusEnum statusEnum;

    @ApiModelProperty(value = "orderDate", dataType = "java.lang.String")
    private Timestamp orderDate;

    @ApiModelProperty(value = "userDTO", dataType = "java.lang.String")
    private UserDTO userDTO;

    @ApiModelProperty(value = "addressDTO", dataType = "java.lang.String")
    private AddressDTO addressDTO;

    @ApiModelProperty(value = "cardDTO", dataType = "java.lang.String")
    private CardDTO cardDTO;
}
