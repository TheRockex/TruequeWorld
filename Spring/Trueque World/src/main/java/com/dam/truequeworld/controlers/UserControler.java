package com.dam.truequeworld.controlers;

import com.dam.truequeworld.models.User;
import com.dam.truequeworld.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Base64;


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
                return setImg(u);
            }
        }
        return null;
    }
    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable Integer id){
        return setImg(userService.getUserById(id));
    }

    @PostMapping("/save")
    public User insertUser(@RequestBody User user){
        String path = "imgs/"+user.getName();
        byte[] img = Base64.getMimeDecoder().decode(user.getImgPerfil());
        File file = new File(path+".jpg");
        try {
            int i = 1;
            while(!file.createNewFile()){
                file = new File(path+i+".jpg");
                i++;
            }
            OutputStream os = new FileOutputStream(file,false);
            os.write(img);
            os.close();
            user.setImgPerfil(file.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @PostMapping("/buscar-o-insertar")
    public ResponseEntity<User> buscarOInsertarUsuario(@RequestBody User newUser) {
        String gmail = newUser.getEmail();
        User existingUser = userService.getUsuarioByGmail(gmail);
        if (existingUser != null) {
            return ResponseEntity.ok(existingUser); // Usuario encontrado (código de estado 200 OK)
        } else {
            userService.saveUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser); // Usuario insertado (código de estado 201 Created)
        }
    }

    //RECUERDEN LLAMAR A ESTE METODO ANTES DE ENVIAR UN USUARIO AL CLIENTE
    private User setImg(User user){
        File file = new File(user.getImgPerfil());
        try {
            byte[] bytesFile = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesFile);
            fis.close();
            user.setImgPerfil(Base64.getMimeEncoder().encodeToString(bytesFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
