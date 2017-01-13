package mx.clickfactura.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SISTEMAS03-PC on 12/01/2017.
 */
@Data
public class ApiError {

    private HttpStatus status;
    private String message;
//    private List<String> errors;

    //

    public ApiError() {
        super();
    }

    public ApiError(final HttpStatus status, final String message) {
        super();
        this.status = status;
        this.message = message;
//        this.errors = errors;
    }

//    public ApiError(final HttpStatus status, final String message, final String error) {
//        super();
//        this.status = status;
//        this.message = message;
//        errors = Arrays.asList(error);
//    }
}
