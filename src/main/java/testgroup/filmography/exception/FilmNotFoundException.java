package testgroup.filmography.exception;

public class FilmNotFoundException extends RuntimeException {

    public FilmNotFoundException(int id) {
        super("Could not find film " + id);
    }

}
