package com.tecsup.backendusuarios.controller;

import com.tecsup.backendusuarios.model.Evento;
import com.tecsup.backendusuarios.model.User;
import com.tecsup.backendusuarios.model.Carrera;
import com.tecsup.backendusuarios.repository.EventoRepository;
import com.tecsup.backendusuarios.repository.UserRepository;
import com.tecsup.backendusuarios.repository.CarreraRepository;
import com.tecsup.backendusuarios.security.CurrentUser;
import com.tecsup.backendusuarios.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createEvento(@RequestBody Evento evento, @CurrentUser UserPrincipal currentUser) {
        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        evento.setAutor(user);
        return ResponseEntity.ok(eventoRepository.save(evento));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento eventoDetails, 
            @CurrentUser UserPrincipal currentUser) {
        return eventoRepository.findById(id)
                .map(evento -> {
                    if (!evento.getAutor().getId().equals(currentUser.getId())) {
                        throw new RuntimeException("No tienes permiso para editar este evento");
                    }
                    if (eventoDetails.getTitulo() != null) evento.setTitulo(eventoDetails.getTitulo());
                    if (eventoDetails.getDescripcion() != null) evento.setDescripcion(eventoDetails.getDescripcion());
                    if (eventoDetails.getFechaInicio() != null) evento.setFechaInicio(eventoDetails.getFechaInicio());
                    if (eventoDetails.getFechaFin() != null) evento.setFechaFin(eventoDetails.getFechaFin());
                    if (eventoDetails.getUbicacion() != null) evento.setUbicacion(eventoDetails.getUbicacion());
                    if (eventoDetails.getImagen() != null) evento.setImagen(eventoDetails.getImagen());
                    if (eventoDetails.getCarrera() != null) evento.setCarrera(eventoDetails.getCarrera());
                    return ResponseEntity.ok(eventoRepository.save(evento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteEvento(@PathVariable Long id, @CurrentUser UserPrincipal currentUser) {
        return eventoRepository.findById(id)
                .map(evento -> {
                    if (!evento.getAutor().getId().equals(currentUser.getId())) {
                        return ResponseEntity.badRequest().body("No tienes permiso para eliminar este evento");
                    }
                    eventoRepository.delete(evento);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/autor/{userId}")
    public List<Evento> getEventosByAutor(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return eventoRepository.findByAutor(user);
    }

    @GetMapping("/carrera/{carreraId}")
    public List<Evento> getEventosByCarrera(@PathVariable Long carreraId) {
        Carrera carrera = carreraRepository.findById(carreraId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        return eventoRepository.findByCarrera(carrera);
    }

    @GetMapping("/fechas")
    public List<Evento> getEventosByFecha(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fin) {
        return eventoRepository.findByFechaInicioBetween(inicio, fin);
    }
}
