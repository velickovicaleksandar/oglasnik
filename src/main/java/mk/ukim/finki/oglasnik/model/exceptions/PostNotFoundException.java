package mk.ukim.finki.oglasnik.model.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(Long id) {
        super(String.format("Post with id:{} not found",id));
    }
}
