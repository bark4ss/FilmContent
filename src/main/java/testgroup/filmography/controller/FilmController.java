package testgroup.filmography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testgroup.filmography.exception.FilmNotFoundException;
import testgroup.filmography.exception.PageNotFoundException;
import testgroup.filmography.model.Film;
import testgroup.filmography.service.FilmService;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
//https://javastudy.ru/spring-mvc/spring-mvc-basic/
//https://www.tutorialspoint.com/spring/spring_mvc_hello_world_example.htm
//https://www.journaldev.com/14476/spring-mvc-example
//https://www.youtube.com/watch?v=1vyf-_5OkW8
//https://javarush.ru/groups/posts/1956-ot-hello-world-do-spring-web-mvc-i-pri-chjem-tut-servrletih
//https://java-master.com/spring-mvc-%D0%BF%D0%B5%D1%80%D0%B2%D0%BE%D0%B5-%D0%B2%D0%B5%D0%B1-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5/
//https://javarush.ru/groups/posts/2253-znakomstvo-s-maven-spring-mysql-hibernate-i-pervoe-crud-prilozhenie-chastjh-1
@Controller
public class FilmController {
    private int page;

    private FilmService filmService;

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms(@RequestParam(defaultValue = "1") int page) {
        if(page == 16) {
            throw new FilmNotFoundException(LocalTime.of(12,00), "midday", page);
        }
        if(page == 17) {
            throw new PageNotFoundException(page);
        }
        List<Film> films = filmService.allFilms(page);
        int filmsCount = filmService.filmsCount();
        int pagesCount = (filmsCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("page", page);
        modelAndView.addObject("filmsList", films);
        modelAndView.addObject("filmsCount", filmsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        if (filmService.checkTitle(film.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            filmService.add(film);
        } else {
            modelAndView.addObject("message","part with title \"" + film.getTitle() + "\" already exists");
            modelAndView.setViewName("redirect:/add");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id,
                                 @ModelAttribute("message") String message) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        if (filmService.checkTitle(film.getTitle()) || filmService.getById(film.getId()).getTitle().equals(film.getTitle())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            filmService.edit(film);
        } else {
            modelAndView.addObject("message","part with title \"" + film.getTitle() + "\" already exists");
            modelAndView.setViewName("redirect:/edit/" +  + film.getId());
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        int filmsCount = filmService.filmsCount();
        int page = ((filmsCount - 1) % 10 == 0 && filmsCount > 10 && this.page == (filmsCount + 9)/10) ?
                this.page - 1 : this.page;
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("page", page);
        Film film = filmService.getById(id);
        filmService.delete(film);
        return modelAndView;
    }

    @GetMapping("/test")
    public void testHandler2 (@RequestParam int exc) {
        if(exc == 1) {
            throw new RuntimeException("runtime exception");
        }
        if(exc == 2) {
            throw new FilmNotFoundException(LocalTime.now(), "film not found by id", exc);
        }
    }
}
