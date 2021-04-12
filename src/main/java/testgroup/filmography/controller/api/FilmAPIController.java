package testgroup.filmography.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import testgroup.filmography.exception.FilmNotFoundException;
import testgroup.filmography.model.Film;
import testgroup.filmography.service.FilmService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class FilmAPIController {

    //Useful resource - https://habr.com/ru/post/471140/
    //https://mincong.io/2019/03/19/jackson-xml-mapper/
    //https://zetcode.com/springboot/restxml/
    //https://www.baeldung.com/exception-handling-for-rest-with-spring
    //https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application

    private FilmService filmService;

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping (value = "/film/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseBody
    public Film oneFilm(@PathVariable int id) {
        try {
            throw new FilmNotFoundException(id);
            //return filmService.getById(id);
        }
        catch (FilmNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Film Not Found", exc);
        }
    }

    @GetMapping (value = "/films/{pageId}", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Film> allFilms(@PathVariable int pageId) {
        return filmService.allFilms(pageId);
    }

    @GetMapping (value = "/films")
    public List<Film> allFilmsWithParams(@RequestParam(value = "id") int pageId) {
        return filmService.allFilms(pageId);
    }

    @GetMapping (value = "/films/{format}/{pageId}")
    public ResponseEntity<List<Film>> service(@PathVariable int pageId, @PathVariable("format") String format) {
        List<Film> films = filmService.allFilms(pageId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType("json".equals(format) ? MediaType.APPLICATION_JSON : MediaType.APPLICATION_XML);
        return new ResponseEntity<>(films, headers, HttpStatus.OK);
    }

    @PostMapping (value = "/film/createnew")
    public Film createFilm(@RequestBody Film film) {
        filmService.add(film);
        return film;
    }

    @DeleteMapping (value = "/film/delete")
    public Film deleteFilm(@RequestBody Film film) {
        filmService.delete(film);
        return film;
    }

    @GetMapping(value="/csrf-token")
    public @ResponseBody String getCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
        return token.getToken();
    }

}
