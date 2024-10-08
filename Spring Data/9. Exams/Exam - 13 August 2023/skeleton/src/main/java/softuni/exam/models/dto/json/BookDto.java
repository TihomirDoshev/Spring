package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.Genre;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class BookDto {
    @Expose
    @Size(min = 3,max = 40)
    @NotNull
    private String author;
    @Expose
    @NotNull
    private boolean available;
    @Expose
    @Size(min = 5)
    @NotNull
    private String description;
    @Expose
    @NotNull
    private Genre genre;
    @Expose
    @Size(min = 3,max = 40)
    @NotNull
    private String title;
    @Expose
    @Positive
    @NotNull
    private Double rating;

    public BookDto() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
