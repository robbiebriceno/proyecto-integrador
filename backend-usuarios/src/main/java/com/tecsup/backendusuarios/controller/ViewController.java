package com.tecsup.backendusuarios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/anuncios")
    public String anuncios() {
        return "anuncios";
    }
}
