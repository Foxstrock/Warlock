package com.warlock.warlock.Book;


import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Book")
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue
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
    String prezzo;

    public Book(Integer id, String materia, String titolo, String distributore, String scuola, String isbn, String prezzo) {
        this.id = id;
        this.materia = materia;
        this.titolo = titolo;
        this.distributore = distributore;
        this.scuola = scuola;
        this.isbn = isbn;
        this.prezzo = prezzo;
    }

    public Book() {
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

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }
}
