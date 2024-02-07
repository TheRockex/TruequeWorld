package com.example.truequeworld.Class;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String dni;

    private String name;

    private String apellidos;

    private String email;

    private String contrasenia;

    private Integer tp;

    private String preferencias;

    public User(Integer id, String dni, String name, String apellidos, String email, String contrasenia, Integer tp, String preferencias) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasenia = contrasenia;
        this.tp = tp;
        this.preferencias = preferencias;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
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

    public Integer getTp() {
        return tp;
    }

    public void setTp(Integer tp) {
        this.tp = tp;
    }
}
