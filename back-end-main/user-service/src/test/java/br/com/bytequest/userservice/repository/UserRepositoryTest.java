package br.com.bytequest.userservice.repository;

import br.com.bytequest.userservice.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private User user;

    @Test
    void create() {
        // Given / Arrange
        User user = new User("Ana Beatriz", "bia@email.com", "123456");

        // When / Act
        var newUser = repository.save(user);

        // Then / Assert
        assertNotNull(newUser, () -> "Não foi registrado o usuário");
        assertTrue(newUser.getId() > 0); // Foi criado um ID
    }

    @Test
    void listAll() {
        // Given / Arrange
        User user = new User("Ana Beatriz", "bia@email.com", "123456");
        User user2 = new User("Ana Luiza", "luiza@email.com", "123456");

        // When / Act
        repository.save(user);
        repository.save(user2);
        List<User> userList = repository.findAll();

        // Then / Assert
        assertNotNull(userList, ()-> "Não foi registrado o usuário");
        assertTrue(userList.size() >= 2);

    }

    @Test
    void listById() {
        // Given / Arrange
        User user = new User("Ana Beatriz", "bia@email.com", "aabbcc");
        var newUser = repository.save(user);

        // When / Act
        var newUserById = repository.findById(newUser.getId()).get();

        // Then / Assert
        assertNotNull(newUserById, ()-> "Usuario não foi encontrado");
        assertEquals(newUser.getId(), newUserById.getId());
    }

    @Test
    void update() {
        // Given / Arrange
        User user = new User("Ana Beatriz", "bia@email.com", "123456");
        var newUser = repository.save(user);

        // When / Act
        var userById = repository.findById(newUser.getId()).get();
        userById.setName("Ana Luiza");
        userById.setEmail("luiza@email.com");
        userById.setPassword_hash("aabbcc");

        var updateUser = repository.save(userById);

        // Then / Assert
        assertNotNull(updateUser, ()-> "Usuário esta nulo");
        assertEquals("Ana Luiza", updateUser.getName());
        assertEquals("luiza@email.com", updateUser.getEmail());
        assertEquals("aabbcc", updateUser.getPassword_hash());
    }

    @Test
    void delete() {
        // Given / Arrange
        User user = new User("Ana Beatriz", "bia@email.com", "123456");
        var newUser = repository.save(user);

        // When / Act
        repository.deleteById(newUser.getId());

        Optional<User> produtoById = repository.findById(newUser.getId());

        // Then / Assert
        assertTrue(produtoById.isEmpty(), () -> "Usuário não foi deletado corretamente");
    }
}