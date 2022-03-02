package amiral.aokiji.tijara.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "Address", description = "Customer Address")
public class AddressDTO {

    @ApiModelProperty(value = "address id", dataType = "java.lang.String")
    private String id;

    @ApiModelProperty(value = "Address number", dataType = "java.lang.String")
    private String number;

    @ApiModelProperty(value = "Address residency", dataType = "java.lang.String")
    private String residency;

    @ApiModelProperty(value = "Address street", dataType = "java.lang.String")
    private String street;

    @ApiModelProperty(value = "Address city", dataType = "java.lang.String")
    private String city;

    @ApiModelProperty(value = "Address city", dataType = "java.lang.String")
    private String state;

    @ApiModelProperty(value = "Address country", dataType = "java.lang.String")
    private String country;

    @ApiModelProperty(value = "Address country", dataType = "java.lang.String")
    private String pincode;
}
