package pl.kacper.backend.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class Topic implements Identify{
    private String content;
    private User author;
    private LocalDateTime timeOfRegistration;

    public Topic(String content, User author, LocalDateTime timeOfRegistration) {
        this.content = content;
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
                Objects.equals(author, topic.author) &&
                Objects.equals(timeOfRegistration, topic.timeOfRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, author, timeOfRegistration);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "content='" + content + '\'' +
                ", author=" + author +
                ", timeOfRegistration=" + timeOfRegistration +
                '}';
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}