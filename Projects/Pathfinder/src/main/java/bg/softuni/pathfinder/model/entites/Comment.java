package bg.softuni.pathfinder.model.entites;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{
    @Column(nullable = false)
    private boolean approved;
    @Column(nullable = false)

    private Instant created;

    @Column(name = "text_content",columnDefinition = "TEXT",nullable = false)
    private String textContent;
    @ManyToOne
    private User author;
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Route route;

    public Comment() {

    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
