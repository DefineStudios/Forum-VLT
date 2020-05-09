package pl.kacper.backend.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.classfile.Code;
import pl.kacper.backend.exception.CodeException;
import pl.kacper.backend.exception.DomainException;
import pl.kacper.backend.model.Comment;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class CommentRepository {
    private Long id;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Set<Comment> comments;
    private File sourceFile;

    public CommentRepository(File sourceFile) throws IOException {
        this.sourceFile = sourceFile;
        comments = getAll();
        setIdBasedOnStatus();
    }

    private void setIdBasedOnStatus(){
        id = comments.stream()
                .mapToLong(Comment::getId)
                .max()
                //.orElseThrow(NoSuchElementException::new);
                .orElse(0L) + 1L;
    }

    public Set<Comment> getAll() throws IOException {
        return objectMapper.readValue(sourceFile, new TypeReference<Set<Comment>>() {});
    }

    public Comment get(long id){
        return comments.stream()
                .filter(c -> c.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new DomainException(CodeException.COMMENT_NOT_FOUND));
    }

    public void save(Comment comment) throws IOException {
        if(comments == null){
            comments = new HashSet<>();
        }
        boolean commentExists = comments.stream()
                .filter(c -> comment.getUser().equals(c.getUser()))
                .filter(c -> comment.getParentTopic().equals(c.getParentTopic()))
                .anyMatch(c -> comment.getContent().equals(c.getContent()));
        if(!commentExists){
            comment.setId(id++);
            comments.add(comment);
            objectMapper.writeValue(sourceFile,comments);
        }
        else {
            throw new DomainException(CodeException.COMMENT_DUPLICATE_FOUND);
        }
    }

    public void clear() throws IOException {
        objectMapper.writeValue(sourceFile,new HashSet<Comment>());
    }

    //getAll
    //getOne
    //save
    //clear
    //modify
    //saveAll
}
