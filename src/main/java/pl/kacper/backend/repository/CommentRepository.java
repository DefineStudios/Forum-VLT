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

public class CommentRepository extends Repository<Comment>{

    public CommentRepository(File sourceFile) throws IOException {
        super(sourceFile);
    }

    public void save(Comment object) throws IOException {
        super.save(object, false);
    }

    //getAll
    //getOne
    //save
    //clear
    //modify
    //saveAll
}
