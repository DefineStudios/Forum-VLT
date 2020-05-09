package pl.kacper.backend.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kacper.backend.exception.CodeException;
import pl.kacper.backend.exception.DomainException;
import pl.kacper.backend.model.Comment;
import pl.kacper.backend.model.Identify;
import pl.kacper.backend.model.User;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract class Repository<T extends Identify> {
    private Long id;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Set<T> objects;
    private File sourceFile;

    public Repository(File sourceFile) throws IOException {
        this.sourceFile = sourceFile;
        objects = getAll();
        setIdBasedOnStatus();
    }

    private void setIdBasedOnStatus(){
        id = objects.stream()
                .mapToLong(T::getId)
                .max()
                //.orElseThrow(NoSuchElementException::new);
                .orElse(0L) + 1L;
    }

    public Set<T> getAll() throws IOException {
        return objectMapper.readValue(sourceFile, new TypeReference<Set<T>>(){});
    }

    public T get(long id){
        return objects.stream()
                .filter(c -> c.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new DomainException(CodeException.COMMENT_NOT_FOUND));
    }

    protected void save(T object, boolean objectExists) throws IOException {
        if(objects == null){
            objects = new HashSet<>();
        }
//        boolean objectExists = objects.stream()
//                .filter(c -> object.getUser().equals(c.getUser()))
//                .filter(c -> object.getParentTopic().equals(c.getParentTopic()))
//                .anyMatch(c -> object.getContent().equals(c.getContent()));
        if(!objectExists){
            object.setId(id++);
            objects.add(object);
            objectMapper.writeValue(sourceFile,objects);
        }
        else {
            throw new DomainException(CodeException.COMMENT_DUPLICATE_FOUND);
        }
    }

    public void clear() throws IOException {
        objectMapper.writeValue(sourceFile,new HashSet<Comment>());
    }
}
