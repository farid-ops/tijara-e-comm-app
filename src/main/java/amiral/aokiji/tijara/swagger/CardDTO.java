package amiral.aokiji.tijara.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "card")
@NoArgsConstructor
@Getter
@Setter
public class CardDTO {

    @ApiModelProperty(value = "card id", dataType = "java.lang.String")
    private String id;

    @ApiModelProperty(value = "card number", dataType = "java.lang.String", required = true)
    private String number;

    @ApiModelProperty(value = "card expires", dataType = "java.lang.String", required = true)
    private String expires;

    @ApiModelProperty(value = "card cvv", dataType = "java.lang.String")
    private String cvv;

    @ApiModelProperty(value = "user dto", dataType = "aokiji.tijara.model.swagger.UserDTO")
    private UserDTO userDTO;
}
