package microservices.menuservice.exceptions;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String message) {super(message);}
}
