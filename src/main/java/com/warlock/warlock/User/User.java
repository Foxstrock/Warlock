package com.warlock.warlock.User;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "User")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "idUser")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    public User(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
