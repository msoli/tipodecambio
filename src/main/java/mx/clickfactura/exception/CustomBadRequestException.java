package mx.clickfactura.exception;


public class CustomBadRequestException extends RuntimeException {

    private static final long serialVersionUID = -5863944037480658215L;


    public CustomBadRequestException(String message) {
        super(message);
    }


    public CustomBadRequestException(Throwable cause) {
        super(cause);
    }

    public CustomBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
