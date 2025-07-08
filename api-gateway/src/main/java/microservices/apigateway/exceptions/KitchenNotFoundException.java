package microservices.apigateway.exceptions;

public class KitchenNotFoundException extends RuntimeException {
    public KitchenNotFoundException(String message) {super(message);}
}
