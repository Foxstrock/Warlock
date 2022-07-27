package com.warlock.warlock.Transiction;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @Column(name = "prezzoVendita")
    Double prezzoVendita;

    @Column(name = "DataIns")
    Date DataIns;

    @Column(name = "DateUsc")
    Date DateUsc;

    @Column(name = "NamePrenotation")
    String NamePrenotation;

    public Transiction(Integer idTransiction, Integer idBook, Integer idUser, String status, Double prezzoVendita, Date dataIns, Date dateUsc, String namePrenotation) {
        this.idTransiction = idTransiction;
        this.idBook = idBook;
        this.idUser = idUser;
        this.status = status;
        this.prezzoVendita = prezzoVendita;
        DataIns = dataIns;
        DateUsc = dateUsc;
        NamePrenotation = namePrenotation;
    }

    public Transiction() {
    }

    public Integer getIdTransiction() {
        return idTransiction;
    }

    public void setIdTransiction(Integer idTransiction) {
        this.idTransiction = idTransiction;
    }

    public Integer getidBook() {
        return idBook;
    }

    public void setBook(Integer book) {
        this.idBook = book;
    }

    public Integer getidUser() {
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

    public Double getPrezzoVendita() {
        return prezzoVendita;
    }

    public void setPrezzoVendita(Double prezzoVendita) {
        this.prezzoVendita = prezzoVendita;
    }

    public String getNamePrenotation() {
        return NamePrenotation;
    }

    public void setNamePrenotation(String namePrenotation) {
        NamePrenotation = namePrenotation;
    }
}
