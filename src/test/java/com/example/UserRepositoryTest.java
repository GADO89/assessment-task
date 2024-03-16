package com.example;

import com.example.dao.UserRepository;
import com.example.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
        (replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {


   @Autowired
   private UserRepository repo;

   @Test
   public void testCreateNewUser(){
      User user= new User("john", "default.png", "john123");
      User savedUser= repo.save(user);
      assertThat(savedUser).isNotNull();

   }
}
