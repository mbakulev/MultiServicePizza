package microservices.apigateway.exceptions;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(String message) {super(message);}
}
