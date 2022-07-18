package com.warlock.warlock.Transiction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransictionRepository extends JpaRepository<Transiction,Integer> {

    List<Transiction> findAllByIdUser(Integer idUser);
}
