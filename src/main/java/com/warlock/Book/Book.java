package com.warlock.Book;


 import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Book")
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access =  JsonProperty.Access.READ_ONLY)
    @Column(name = "idBook")
    Integer id;
    @Column(name = "materia")
    String materia;
    @Column(name = "titolo")
    String titolo;
    @Column(name = "distributore")
    String distributore;
    @Column(name = "scuola")
    String scuola;
    @Column(name = "isbn")
    String isbn;
    @Column(name = "prezzoOriginale")
    Double prezzo;
    @Column(name = "ImageString")
    String imageString;

    @Transient
    String status;

    @Transient
    Integer idTransiction;

    public Book(Integer id, String materia, String titolo, String distributore, String scuola, String isbn, Double prezzo, String imageString, String status, Integer idTransiction) {
        this.id = id;
        this.materia = materia;
        this.titolo = titolo;
        this.distributore = distributore;
        this.scuola = scuola;
        this.isbn = isbn;
        this.prezzo = prezzo;
        this.imageString = imageString;
        this.status = status;
        this.idTransiction = idTransiction;
    }

    public Book() {
    }

    public Book(Book b, String status,Integer idTransiction){
        this.id = b.id;
        this.materia = b.materia;
        this.titolo = b.titolo;
        this.distributore = b.distributore;
        this.scuola = b.scuola;
        this.isbn = b.isbn;
        this.prezzo = b.prezzo;
        this.imageString = b.imageString;
        this.status = status;
        this.idTransiction = idTransiction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDistributore() {
        return distributore;
    }

    public void setDistributore(String distributore) {
        this.distributore = distributore;
    }

    public String getScuola() {
        return scuola;
    }

    public void setScuola(String scuola) {
        this.scuola = scuola;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdTransiction() {
        return idTransiction;
    }

    public void setIdTransiction(Integer idTransiction) {
        this.idTransiction = idTransiction;
    }
}
