package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.User;
import com.dam.truequeworld.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControler {
    @Autowired
    private UserService userService;

    @GetMapping("/login/{usuario}/{contrasenia}")
    public Integer getUserId(@PathVariable String usuario, @PathVariable String contrasenia){
        for (User u : userService.getAllUsers()) {
            if (u.getName().equals(usuario)) {
                if (!u.getContrasenia().equals(contrasenia)) {
                    return null;
                }
                return u.getId();
            }
        }
        return null;
    }
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    public User insertUser(@RequestBody User user){
       return userService.saveUser(user);
    }

    @PutMapping("/save")
    public User updateUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteUserById(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }
}
