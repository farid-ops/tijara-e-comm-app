package amiral.aokiji.tijara.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

@Data
@NoArgsConstructor
@Getter
public class Error {
    @Setter
    private String errorCode;
    @Setter
    private Integer statusCode;
    @Setter
    private String message;
    private String url = "";
    private String reqMethod = "";

    public Error setUrl(String url){
        if (Strings.isNotBlank(url))
            this.url = url;
        return this;
    }

    public Error setReqMethod(String reqMethod){
        if (Strings.isNotBlank(reqMethod))
            this.reqMethod = reqMethod;
        return this;
    }
}
