package testgroup.filmography.model;

import javax.persistence.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

@Entity
@Table(name = "films")
@JacksonXmlRootElement(localName = "Film")
public class Film implements Serializable {

    private static final long serialVersionUID = 21L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JacksonXmlProperty(isAttribute = true)
    private int id;

    @Column(name = "title")
    @JacksonXmlProperty(localName = "name")
    private String title;

    @Column(name = "year")
    @JacksonXmlProperty
    private int year;

    @Column(name = "genre")
    @JacksonXmlProperty
    private String genre;

    @Column(name = "watched")
    @JacksonXmlProperty
    private boolean watched;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}
