package pl.kacper.backend.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment implements Identify<Comment>{
    private String content;
    private User user;
    private LocalDateTime timeOfAdd;
    private boolean edited;
    private Long id;
    private Topic parentTopic;

    public Comment(String content, User user, LocalDateTime timeOfAdd, boolean edited, Topic parentTopic) {
        this.content = content;
        this.user = user;
        this.timeOfAdd = timeOfAdd;
        this.edited = edited;
        this.parentTopic = parentTopic;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Topic getParentTopic() {
        return parentTopic;
    }

    public void setParentTopic(Topic parentTopic) {
        this.parentTopic = parentTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return edited == comment.edited &&
                Objects.equals(content, comment.content) &&
                Objects.equals(user, comment.user) &&
                Objects.equals(timeOfAdd, comment.timeOfAdd) &&
                Objects.equals(parentTopic, comment.parentTopic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, user, timeOfAdd, edited, parentTopic);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", user=" + user +
                ", timeOfAdd=" + timeOfAdd +
                ", edited=" + edited +
                ", id=" + id +
                ", parentTopic=" + parentTopic +
                '}';
    }

    @Override
    public int compareTo(Comment o) {
        return o.getTimeOfAdd().compareTo(this.getTimeOfAdd());
    }
}
