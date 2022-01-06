package amiral.aokiji.tijara.exceptions;

import lombok.NoArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@NoArgsConstructor
@Getter
public class Error {
    private String errorCode;
    private String message;
    private Integer statusCode;
    private String url = "Not available";
    private String reqMethod = "Not available";

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Error setUrl(String url) {
        if (Strings.isNotBlank(url))
            this.url = url;
        return this;
    }

    public Error setReqMethod(String reqMethod) {
        if (Strings.isNotBlank(reqMethod))
            this.reqMethod = reqMethod;
        return this;
    }
}
