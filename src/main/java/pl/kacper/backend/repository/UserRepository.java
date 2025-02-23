package pl.kacper.backend.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kacper.backend.exception.CodeException;
import pl.kacper.backend.exception.DomainException;
import pl.kacper.backend.model.User;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UserRepository {
    private Long id = 1L;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Set<User> users;
    private File sourceFile;

    public UserRepository(File file) {
        sourceFile = file;
    }

    public Set<User> getAll() throws IOException { ///pobranie wszystkich userów i przypisanie ich do users
        return objectMapper.readValue(sourceFile, new TypeReference<Set<User>>() {});
    }

    public void save(User user) throws IOException { ///zapisywanie nowego rekordu do pliku
        getAll();
        if(users == null) {
            users = new HashSet<>();
        }
        boolean userExists = users.stream()
                .anyMatch(u -> u.getLogin().equals(user.getLogin()));
        if(!userExists){
            user.setId(id++);
            users.add(user);
            objectMapper.writeValue(sourceFile, users);
        }
        else{
            throw new DomainException(CodeException.USER_DUPLICATE_FOUND);
        }
    }

    public void clear() throws IOException {
        objectMapper.writeValue(sourceFile,new HashSet<User>());
    }

    public User getUser(Long id) throws IOException {
        return getAll().stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new DomainException(CodeException.USER_NOT_FOUND));
    }

    //kontynuacja CRUDa
}
