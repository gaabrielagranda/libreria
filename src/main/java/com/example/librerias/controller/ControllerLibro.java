package com.example.librerias.controller;

import com.example.librerias.entity.Libro;
import com.example.librerias.services.GestorLibro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/libreria")
public class ControllerLibro {

    GestorLibro gl = new GestorLibro();

    @GetMapping("/list")
    public String listBooks(Model model) {
        try {
            model.addAttribute("lista", gl.getAll());
        } catch (SQLException e) {
            return "./alerts/error";
        }
        return "./libros/list"; // Devuelve el nombre de la vista list.html
    }

    @GetMapping("/formInsert")
    public String formInsert(Model model) {
        Libro libro = new Libro(); // Crear una instancia de Libro
        model.addAttribute("libro", libro);
        return "./libros/list"; // Devuelve el nombre del archivo HTML del formulario
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute Libro libro) {
        try {
            gl.insert(libro); // Inserta el nuevo libro en la base de datos
        } catch (SQLException e) {
            // Manejar el error adecuadamente, redirigiendo a la página de formulario de inserción con un mensaje de error
            return "./alerts/error";
        }
        return "redirect:/libros/list";
    }

    @GetMapping("/formUpdate/{id}")
    public String formUpdate(@PathVariable int id, Model model) {
        try {
            Libro libro = gl.getById(id);
            if (libro != null) {
                model.addAttribute("libro", libro);
                return "./libros/formUpdate";
            } else {
                return "redirect:/libros/list";
            }
        } catch (SQLException e) {
            return "./alerts/error";
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Libro libro) {
        try {
            gl.update(libro);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/libros/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        try {
            Libro libro = new Libro(); // Crear un libro con el ID proporcionado para eliminar
            gl.delete(libro);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/libros/list";
    }

    @GetMapping("/")
    public String home() {
        return "./libros/Home";
    }
}

