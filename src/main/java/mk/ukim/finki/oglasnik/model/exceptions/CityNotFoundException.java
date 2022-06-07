package mk.ukim.finki.oglasnik.model.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(Long cityId) {
        super(String.format("City with id {} not found",cityId));
    }
}
