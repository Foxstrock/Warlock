package com.warlock.warlock.User;

import com.warlock.warlock.Book.Book;

import java.util.List;

public class UserDto {


    Integer id;

    String name;

    String surname;

    List<Book> bookList;

    public UserDto(Integer id, String name, String surname, List<Book> bookList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.bookList = bookList;
    }

    public UserDto() {
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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
