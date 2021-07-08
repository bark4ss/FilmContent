package testgroup.filmography.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;

//https://examples.javacodegeeks.com/enterprise-java/spring/mvc/spring-mvc-controlleradvice-annotation-example/
//https://howtodoinjava.com/spring-core/spring-exceptionhandler-annotation/
//https://howtodoinjava.com/spring-core/spring-exceptionhandler-annotation/
@ControllerAdvice
public class FilmNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(FilmNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {

        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", ex.getMessage());
        model.setViewName("404");
        return model;

    }

    @ExceptionHandler({FilmNotFoundException.class})
    public ModelAndView handleMyException(FilmNotFoundException mex) {

        ModelAndView model = new ModelAndView();
        model.addObject("exception", mex);
        model.setViewName("filmError");
        return model;
    }
}
