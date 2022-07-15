package com.warlock.warlock.Book;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Book")
public class Book {
    @Id
    String id;
    @Column(name = "MATERIA")
    String materia;
    @Column(name = "TITOLO")
    String titolo;
    @Column(name = "DISTRIBUTORE")
    String distributore;
    @Column(name = "SCUOLA")
    String scuola;
    @Column(name = "ISBN")
    String isbn;
    @Column(name = "PREZZO ORIGINALE")
    String prezzo;

    public Book(String id, String materia, String titolo, String distributore, String scuola, String isbn, String prezzo) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
