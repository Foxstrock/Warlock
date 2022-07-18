package com.warlock.warlock.Transiction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.warlock.warlock.Book.Book;
import com.warlock.warlock.User.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "Transiction")
public class Transiction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTransiction")
    Integer idTransiction;

    @Column(name = "idBook")
    Integer idBook;

    @Column(name = "idUser")
    Integer idUser;

    @Column(name = "status")
    String status;

    @Column(name = "DataIns")
    Date DataIns;

    @Column(name = "DateUsc")
    Date DateUsc;

    public Transiction(Integer idTransiction, Integer idBook, Integer idUser, String status, Date dataIns, Date dateUsc) {
        this.idTransiction = idTransiction;
        this.idBook = idBook;
        this.idUser = idUser;
        this.status = status;
        DataIns = dataIns;
        DateUsc = dateUsc;
    }

    public Transiction() {
    }

    public Integer getIdTransiction() {
        return idTransiction;
    }

    public void setIdTransiction(Integer idTransiction) {
        this.idTransiction = idTransiction;
    }

    public Integer getBook() {
        return idBook;
    }

    public void setBook(Integer book) {
        this.idBook = book;
    }

    public Integer getUser() {
        return idUser;
    }

    public void setUser(Integer user) {
        this.idUser = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataIns() {
        return DataIns;
    }

    public void setDataIns(Date dataIns) {
        DataIns = dataIns;
    }

    public Date getDateUsc() {
        return DateUsc;
    }

    public void setDateUsc(Date dateUsc) {
        DateUsc = dateUsc;
    }
}
