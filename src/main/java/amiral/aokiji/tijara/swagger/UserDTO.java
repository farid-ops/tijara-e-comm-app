package amiral.aokiji.tijara.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "user entity")
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    @ApiModelProperty(value = "user id", dataType = "java.lang.String")
    private String id;

    @ApiModelProperty(value = "user name", dataType = "java.lang.String", required = true)
    private String username;

    @ApiModelProperty(value = "user password", dataType = "java.lang.String", required = true)
    private String password;

    @ApiModelProperty(value = "user firstname", dataType = "java.lang.String", required = true)
    private String firstname;

    @ApiModelProperty(value = "user last name", dataType = "java.lang.String")
    private String lastname;

    @ApiModelProperty(value = "user phone", dataType = "java.lang.String")
    private String phone;

    @ApiModelProperty(value = "user status", dataType = "java.lang.String")
    private String userstatus;

    @ApiModelProperty(value = "cart entity ref")
    private CartDTO cartDTO;

}
