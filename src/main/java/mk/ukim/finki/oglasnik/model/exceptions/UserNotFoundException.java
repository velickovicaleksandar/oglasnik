package mk.ukim.finki.oglasnik.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(String.format("User with username {} not found",username));
    }
}
