package pl.kacper.backend.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.kacper.backend.model.Comment;
import pl.kacper.backend.model.Topic;
import pl.kacper.backend.model.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CommentRepositoryTest {

    private CommentRepository commentRepository;

    @Before
    public void setUp() throws Exception {
        commentRepository = new CommentRepository(new File("src/test/resources/comments.json"));
    }

    @After
    public void tearDown() throws Exception {
        commentRepository.clear();
    }

    @Test
    public void getAll() throws IOException {
        int size = commentRepository.getAll().size();
        assertEquals(0, size);
    }

    @Test
    public void get() throws IOException {
        Comment beforeSave = new Comment("test", new User("test","test"),null,false,new Topic());
        commentRepository.save(beforeSave);
        assertEquals(beforeSave,commentRepository.get(beforeSave.getId()));
    }

    @Test
    public void isTheIdCorrectlySetBasedOnDatabaseStatus() throws IOException {
        Comment beforeSave = new Comment("testing", new User("aaa","aaa"),null,false,new Topic());
        CommentRepository commentRepository = new CommentRepository(new File("src/test/resources/commentIdTest.json"));
        commentRepository.save(beforeSave);
        assertEquals(true,beforeSave.getId()>2L);
    }
}
//2 objects save to repo
//c new repo from comjson
//check AE