package testgroup.filmography.exception;

import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class FilmNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private LocalTime date;
    private String message;
    private int filmId;

    public FilmNotFoundException() {
    }

    public FilmNotFoundException(int filmId) {
        super();
        this.filmId = filmId;
    }

    public FilmNotFoundException(LocalTime date, String message, int filmId) {
        super();
        this.date = date;
        this.message = message;
        this.filmId = filmId;
    }

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }
}
