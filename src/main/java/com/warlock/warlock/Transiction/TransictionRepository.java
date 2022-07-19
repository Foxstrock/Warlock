package com.warlock.warlock.Transiction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransictionRepository extends JpaRepository<Transiction,Integer> {

    List<Transiction> findAllByIdUser(Integer idUser);

    @Query(value = "SELECT idBook FROM Transiction WHERE idUser = ?1")
    List<Integer> findBooksIdFromUserId(Integer idUser);

    @Query(value = "SELECT idUser FROM Transiction WHERE idBook = ?1")
    List<Integer> findUserIdFromBookId(Integer idBook);
}
