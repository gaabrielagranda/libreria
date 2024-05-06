package com.example.librerias.services;
import com.example.librerias.database.Conexion;
import com.example.librerias.entity.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorLibro {
    Conexion c = new Conexion();

    //Consultas SQL
    final String INSERT = "INSERT INTO libro(titulo, autor, anio_publicacion) VALUES (?,?,?)";
    final String UPDATE = "UPDATE libro SET titulo = ?, autor = ?, anio_publicacion = ? WHERE id = ?";
    final String DELETE = "DELETE FROM libro WHERE id = ?";
    final String GETALL = "SELECT * FROM libro";
    final String GETONE = "SELECT * FROM libro WHERE id = ?";

    public void insert(Libro l) throws SQLException {
        Connection connection = c.getConnect();
        PreparedStatement ps = connection.prepareStatement(INSERT);
        ps.setString(1, l.getTitulo());
        ps.setString(2, l.getAutor());
        ps.setInt(3, l.getAnioPublicacion());
        ps.executeUpdate();
    }

    public void update(Libro l) throws SQLException {
        try (Connection connection = c.conectar();
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setInt(3, l.getAnioPublicacion());
            ps.setInt(4, l.getId());
            ps.executeUpdate();
        }
    }

    public void delete(Libro l) throws SQLException {
        try (Connection connection = c.conectar();
             PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setInt(1, l.getId());
            ps.executeUpdate();
        }
    }

    public List<Libro> getAll() throws SQLException {
        List<Libro> lista = new ArrayList<>();
        try (Connection connection = c.conectar();
             PreparedStatement ps = connection.prepareStatement(GETALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anioPublicacion = rs.getInt("anio_publicacion");
                Libro libro = new Libro();
                lista.add(libro);
            }
        }
        return lista;
    }

    public Libro getById(int id) throws SQLException {
        Libro libro = null;
        try (Connection connection = c.conectar();
             PreparedStatement ps = connection.prepareStatement(GETONE)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    int anioPublicacion = rs.getInt("anio_publicacion");
                    libro = new Libro();
                }
            }
        }
        return libro;
    }
}
