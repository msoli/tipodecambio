package mx.clickfactura.exception;


public class CustomNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4664456874499611218L;


    public CustomNotFoundException(String message) {
        super(message);
    }

    public CustomNotFoundException(Throwable cause) {
        super(cause);
    }

    public CustomNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
