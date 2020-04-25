package pl.kacper.backend.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class Topic {
    private String content;
    private Set<Comment> comments;
    private User author;
    private LocalDateTime timeOfRegistration;

    public Topic(String content, Set<Comment> comments, User author, LocalDateTime timeOfRegistration) {
        this.content = content;
        this.comments = comments;
        this.author = author;
        this.timeOfRegistration = timeOfRegistration;
    }

    public Topic() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getTimeOfRegistration() {
        return timeOfRegistration;
    }

    public void setTimeOfRegistration(LocalDateTime timeOfRegistration) {
        this.timeOfRegistration = timeOfRegistration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(content, topic.content) &&
                Objects.equals(comments, topic.comments) &&
                Objects.equals(author, topic.author) &&
                Objects.equals(timeOfRegistration, topic.timeOfRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, comments, author, timeOfRegistration);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "content='" + content + '\'' +
                ", comments=" + comments +
                ", author=" + author +
                ", timeOfRegistration=" + timeOfRegistration +
                '}';
    }
}