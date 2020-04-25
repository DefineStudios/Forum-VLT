package pl.kacper.backend.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    private String content;
    private User user;
    private LocalDateTime timeOfAdd;
    private boolean edited;

    public Comment(String content, User user, LocalDateTime timeOfAdd, boolean edited) {
        this.content = content;
        this.user = user;
        this.timeOfAdd = timeOfAdd;
        this.edited = edited;
    }

    public Comment() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimeOfAdd() {
        return timeOfAdd;
    }

    public void setTimeOfAdd(LocalDateTime timeOfAdd) {
        this.timeOfAdd = timeOfAdd;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return edited == comment.edited &&
                Objects.equals(content, comment.content) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(timeOfAdd, comment.timeOfAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, user, timeOfAdd, edited);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", user=" + user +
                ", timeOfAdd=" + timeOfAdd +
                ", edited=" + edited +
                '}';
    }
}
