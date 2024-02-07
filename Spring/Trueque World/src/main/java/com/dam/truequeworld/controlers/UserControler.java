package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.User;
import com.dam.truequeworld.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserControler {
    @Autowired
    private UserService userService;

    @GetMapping("/login/{email}/{contrasenia}")
    public User getUserId(@PathVariable String email, @PathVariable String contrasenia){
        for (User u : userService.getAllUsers()) {
            if (u.getEmail().equals(email)) {
                if (!u.getContrasenia().equals(contrasenia)) {
                    return null;
                }
                return u;
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
