package com.warlock.warlock.Book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Book")
public class Book {
    @Id
    String id;
    @Field(name = "MATERIA")
    String materia;
    @Field(name = "TITOLO")
    String titolo;
    @Field(name = "DISTRIBUTORE")
    String distributore;
    @Field(name = "SCUOLA")
    String scuola;
    @Field(name = "ISBN")
    String isbn;
    @Field(name = "PREZZO ORIGINALE")
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
