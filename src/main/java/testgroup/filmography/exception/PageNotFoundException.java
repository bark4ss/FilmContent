package testgroup.filmography.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class PageNotFoundException extends RuntimeException{

    public PageNotFoundException() {
    }

    public PageNotFoundException(int id) {
        super("PageNotFoundException with id: " + id);
    }
}
