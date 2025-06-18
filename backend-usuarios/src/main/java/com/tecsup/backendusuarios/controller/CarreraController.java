package com.tecsup.backendusuarios.controller;

import com.tecsup.backendusuarios.model.Carrera;
import com.tecsup.backendusuarios.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carreras")
@CrossOrigin(origins = "*")
public class CarreraController {

    @Autowired
    private CarreraRepository carreraRepository;

    @GetMapping
    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable Long id) {
        return carreraRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Carrera createCarrera(@RequestBody Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Carrera> updateCarrera(@PathVariable Long id, @RequestBody Carrera carreraDetails) {
        return carreraRepository.findById(id)
                .map(carrera -> {
                    if (carreraDetails.getCodigo() != null) carrera.setCodigo(carreraDetails.getCodigo());
                    if (carreraDetails.getNombre() != null) carrera.setNombre(carreraDetails.getNombre());
                    if (carreraDetails.getDescripcion() != null) carrera.setDescripcion(carreraDetails.getDescripcion());
                    return ResponseEntity.ok(carreraRepository.save(carrera));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCarrera(@PathVariable Long id) {
        return carreraRepository.findById(id)
                .map(carrera -> {
                    carreraRepository.delete(carrera);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Carrera> getCarreraByCodigo(@PathVariable String codigo) {
        return carreraRepository.findByCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
