package pl.kacper.backend.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.kacper.backend.exception.DomainException;
import pl.kacper.backend.model.User;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository = new UserRepository(new File("src/test/resources/users.json"));
    }

    @After
    public void tearDown() throws Exception {
        userRepository.clear();
    }

    @Test
    public void getAll() throws IOException {
        int usersCount = userRepository.getAll().size();
        assertEquals(0, usersCount);
    }

    @Test
    public void save() throws IOException {
        int usersBeforeSave = userRepository.getAll().size();
        userRepository.save(new User("login", "password"));
        int usersAfterSave = userRepository.getAll().size();
        assertEquals(usersBeforeSave+1, usersAfterSave);
    }

    @Test
    public void clear(){
        int usersBeforeClear = 0;
        int usersAfterClear = 0;
        try {
            userRepository.save(new User("test", "user"));
            usersBeforeClear = userRepository.getAll().size();
            userRepository.clear();
            usersAfterClear = userRepository.getAll().size();
        } catch (IOException e) {
            fail("test shouldn't throw exception");
            e.printStackTrace();
        }
        assertEquals(usersBeforeClear > 0, true);
        assertEquals(0,usersAfterClear);
    }

    @Test
    public void getUser(){
        User userResult = null;
        User user = new User("test","user");
        try {
            userRepository.save(user);
            userResult = userRepository.getUser(user.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(user, userResult);
    }

    @Test(expected = DomainException.class)
    public void shouldThrowExceptionWhenUserNotFound(){
        try {
            userRepository.getUser(1L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update(){
        User userResult = new User("AAA","BBB");
        User userBeforeModification = new User("CCC","DDD");
        userBeforeModification.setId(1L);
        userResult.setId(1L);
        try {
            userRepository.save(userBeforeModification);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User checked = null;
        try {
            userRepository.update(userResult);
            checked = userRepository.getUser(userResult.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(userResult,checked);
    }

    @Test
    public void userSortingByIdTest() throws IOException {
        User user1 = new User("AAA","BBB");
        User user2 = new User("BBB","CCC");
        User user3 = new User("CCC","DDD");
        User user4 = new User("DDD","EEE");
        userRepository.save(user3);
        userRepository.save(user1);
        userRepository.save(user4);
        userRepository.save(user2);
        Iterator<User> iterator = userRepository.getAll().iterator();
        assertEquals(user3, iterator.next());
        assertEquals(user1, iterator.next());
        assertEquals(user4, iterator.next());
        assertEquals(user2, iterator.next());
    }
}