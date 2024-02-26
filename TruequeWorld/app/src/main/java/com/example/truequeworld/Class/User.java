package com.example.truequeworld.Class;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String dni;

    private String name;

    private String apellidos;

    private String email;

    private String contrasenia;

    private String imgPerfil;

    private Integer truquepoints;

    private String preferencias;

    private int movil;

    public User(Integer id, String dni, String name, String apellidos, String email, String contrasenia, Integer truquepoints, String imgPerfil, String preferencias, Integer movil) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasenia = contrasenia;
        this.truquepoints = truquepoints;
        this.imgPerfil = imgPerfil;
        this.preferencias = preferencias;
        this.movil = movil;
    }

    public String getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getTruquepoints() {
        return truquepoints;
    }

    public void setTruquepoints(Integer tp) {
        this.truquepoints = tp;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public int getMovil() {
        return movil;
    }

    public void setMovil(int movil) {
        this.movil = movil;
    }
}
