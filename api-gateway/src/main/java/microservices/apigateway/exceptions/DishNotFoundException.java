package microservices.apigateway.exceptions;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String message) {super(message);}
}
