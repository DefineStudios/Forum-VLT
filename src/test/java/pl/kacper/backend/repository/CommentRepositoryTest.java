package pl.kacper.backend.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pl.kacper.backend.model.Comment;
import pl.kacper.backend.model.Topic;
import pl.kacper.backend.model.User;
import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

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
    public void commentSortingByDateOfAddTest() throws IOException {
        Comment c1 = new Comment("test1", new User("test","test"), LocalDateTime.of(2020,5,12,18,24),false,new Topic());
        Comment c2 = new Comment("test2", new User("test","test"),LocalDateTime.of(2020,5,12,17,21),false,new Topic());
        Comment c3 = new Comment("test3", new User("test","test"),LocalDateTime.of(2020,5,14,18,50),false,new Topic());
        Comment c4 = new Comment("test4", new User("test","test"),LocalDateTime.of(2020,5,13,20,55),false,new Topic());
        commentRepository.save(c1);
        commentRepository.save(c2);
        commentRepository.save(c3);
        commentRepository.save(c4);
        Set<Comment> commentsTreeSet = commentRepository.getAll();
        Iterator<Comment> iterator = commentsTreeSet.iterator();
        assertEquals(c2,iterator.next());
        assertEquals(c1,iterator.next());
        assertEquals(c4,iterator.next());
        assertEquals(c3,iterator.next());
    }

    @Ignore
    @Test
    public void isTheIdCorrectlySetBasedOnDatabaseStatus() throws IOException {
        Comment beforeSave = new Comment("testing", new User("aaa","aaa"),null,false,new Topic());
        CommentRepository commentRepository = new CommentRepository(new File("src/test/resources/commentIdTest.json"));
        commentRepository.save(beforeSave);
        assertEquals(true,beforeSave.getId()>2L);
//        Comment comment1 = new Comment("COM1", new User("test","test"),null,false,new Topic());
//        Comment comment2 = new Comment("test", new User("test","test"),null,false,new Topic());
//        commentRepository.save(comment1);
//        commentRepository.save(comment2);
//        commentRepository = new CommentRepository(new File("src/test/resources/comments.json"));
//        Comment comment3 = new Comment("tNOBJ", new User("test","test"),null,false,new Topic());
//        commentRepository.save(comment3);
//        assertEquals(true, comment3.getId() > 2L);
        //commentRepository.save();
    }
}
//2 objects save to repo
//c new repo from comjson
//check AE