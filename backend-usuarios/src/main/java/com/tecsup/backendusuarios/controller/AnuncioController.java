package com.tecsup.backendusuarios.controller;

import com.tecsup.backendusuarios.model.Anuncio;
import com.tecsup.backendusuarios.model.User;
import com.tecsup.backendusuarios.repository.AnuncioRepository;
import com.tecsup.backendusuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anuncios")
@CrossOrigin(origins = "*")
public class AnuncioController {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UserRepository userRepository;

    // Obtener todos los anuncios
    @GetMapping
    public List<Anuncio> getAllAnuncios() {
        return anuncioRepository.findAll();
    }

    // Obtener un anuncio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Anuncio> getAnuncioById(@PathVariable Long id) {
        return anuncioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo anuncio
    @PostMapping
    public Anuncio createAnuncio(@RequestBody Anuncio anuncio) {
        return anuncioRepository.save(anuncio);
    }

    // Actualizar un anuncio
    @PutMapping("/{id}")
    public ResponseEntity<Anuncio> updateAnuncio(@PathVariable Long id, @RequestBody Anuncio anuncioDetails) {
        return anuncioRepository.findById(id)
                .map(anuncio -> {
                    anuncio.setTitulo(anuncioDetails.getTitulo());
                    anuncio.setDescripcion(anuncioDetails.getDescripcion());
                    anuncio.setFecha(anuncioDetails.getFecha());
                    anuncio.setPonente(anuncioDetails.getPonente());
                    return ResponseEntity.ok(anuncioRepository.save(anuncio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un anuncio
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnuncio(@PathVariable Long id) {
        return anuncioRepository.findById(id)
                .map(anuncio -> {
                    anuncioRepository.delete(anuncio);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener anuncios por usuario creador
    @GetMapping("/usuario/{userId}")
    public List<Anuncio> getAnunciosByUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return anuncioRepository.findByCreadoPor(user);
    }
}