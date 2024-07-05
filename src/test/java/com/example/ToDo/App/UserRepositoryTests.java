package com.example.ToDo.App;


import com.example.ToDo.App.user.User;
import com.example.ToDo.App.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("1227050128@student.uinsgd.ac.id");
        user.setPassword("Bandung01");
        user.setFirstName("Siti");
        user.setLastName("Nurbayanah");

        User savedUser = repo.save(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());

    }

    @Test
    public void testListAll() {
       Iterable<User>users  = repo.findAll();
       Assertions.assertNotNull(users);

       for (User user : users) {
           System.out.println(user);
       }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("1-PBO");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();
        Assertions.assertEquals("1-PBO",updatedUser.getPassword());
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();

        Assertions.assertTrue(optionalUser.isPresent());
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);

        Assertions.assertFalse(optionalUser.isPresent());
    }
}
