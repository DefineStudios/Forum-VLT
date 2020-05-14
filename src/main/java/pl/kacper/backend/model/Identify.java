package pl.kacper.backend.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import pl.kacper.backend.repository.CommentRepository;

import java.io.File;

//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Comment.class),
//        @JsonSubTypes.Type(value = Topic.class),
//        @JsonSubTypes.Type(value = CommentRepository.class),
//        @JsonSubTypes.Type(value = Long.class),
//        @JsonSubTypes.Type(value = File.class)
//})
public interface Identify<T> extends Comparable<T>{
    Long getId();
    void setId(Long id);
}
