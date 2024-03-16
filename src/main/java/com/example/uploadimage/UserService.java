package com.example.uploadimage;

import com.example.uploadimage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired private UserRepository repo;

    public User save(User user){
        return repo.save(user);
    }

}
