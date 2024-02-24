package com.dam.truequeworld.servicies;

import com.dam.truequeworld.models.Product;
import com.dam.truequeworld.models.User;
import com.dam.truequeworld.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
         return   userRepository.findAll();
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).get();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Boolean deleteUserById(Integer id){
         userRepository.deleteById(id);
         return userRepository.findById(id).isEmpty();
    }

    public User getUsuarioByGmail(String gmail) {
        return userRepository.findByGmail(gmail);
    }
}
