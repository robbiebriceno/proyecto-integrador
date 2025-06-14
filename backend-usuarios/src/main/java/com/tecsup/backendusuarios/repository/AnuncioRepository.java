package com.tecsup.backendusuarios.repository;

import com.tecsup.backendusuarios.model.Anuncio;
import com.tecsup.backendusuarios.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    List<Anuncio> findByCreadoPor(User user);
}