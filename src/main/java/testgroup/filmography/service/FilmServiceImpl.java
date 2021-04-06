package testgroup.filmography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.filmography.dao.FilmDAO;
import testgroup.filmography.dao.FilmDAOImpl;
import testgroup.filmography.model.Film;

import java.util.List;

@Service
@Transactional
public class FilmServiceImpl implements FilmService{

    @Autowired
    private FilmDAO filmDAO;

    @Override
    public List<Film> allFilms(int page) {
        return filmDAO.allFilms(page);
    }

    @Override
    public void add(Film film) {
        filmDAO.add(film);
    }

    @Override
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    @Override
    public void edit(Film film) {
        filmDAO.edit(film);
    }

    @Override
    public Film getById(int id) {
        return filmDAO.getById(id);
    }

    @Override
    public int filmsCount() {
        return filmDAO.filmsCount();
    }

    @Override
    public boolean checkTitle(String title) {
        return filmDAO.checkTitle(title);
    }
}
