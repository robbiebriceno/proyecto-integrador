package com.tecsup.backendusuarios.controller;

import com.tecsup.backendusuarios.dto.auth.UserInfo;
import com.tecsup.backendusuarios.exception.ResourceNotFoundException;
import com.tecsup.backendusuarios.model.User;
import com.tecsup.backendusuarios.repository.UserRepository;
import com.tecsup.backendusuarios.security.CurrentUser;
import com.tecsup.backendusuarios.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserInfo getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());

        return new UserInfo(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getImageUrl(),
                roles
        );
    }
}