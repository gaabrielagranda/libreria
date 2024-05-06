package com.example.librerias.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class test {
    @GetMapping("/ping")
    public String crud(Model model){
        return "./ping";
    }
}