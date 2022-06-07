package mk.ukim.finki.oglasnik.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username) {
        super(String.format("Username {} already exists",username));
    }
}
