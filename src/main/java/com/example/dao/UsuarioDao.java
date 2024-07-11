package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.coneccion.ConectionDb;
import com.example.entities.UserEntity;;

public class UsuarioDao {

    public boolean insertarUsuario(UserEntity usuario) {
        String sql = "INSERT INTO registro (email, username, password) VALUES (?, ?, ?)";

        try (Connection conn = ConectionDb.obtenerConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getName());
            pstmt.setString(3, usuario.getPassword());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private UserEntity extraerUsuarioDeResultSet(ResultSet rs) throws Exception {
        UserEntity usuario = new UserEntity();
        usuario.setId(rs.getInt("id"));
        usuario.setEmail(rs.getString("email"));
        usuario.setName(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        return usuario;
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> usuarios = new ArrayList<>();
        String query = "SELECT * FROM registro";

        try (Connection conn = ConectionDb.obtenerConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                UserEntity usuario = extraerUsuarioDeResultSet(rs);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public UserEntity obtenerPorId(int id) {
        String query = "SELECT * FROM registro WHERE id = ?";
        try (Connection conn = ConectionDb.obtenerConexion();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extraerUsuarioDeResultSet(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean modificar(UserEntity usuario) {
        String query = "UPDATE registro SET email = ?, username = ?, password = ? WHERE id = ?";
        try (Connection conn = ConectionDb.obtenerConexion();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getName());
            pstmt.setString(3, usuario.getPassword());
            pstmt.setInt(4, usuario.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String query = "DELETE FROM registro WHERE id = ?";
        try (Connection conn = ConectionDb.obtenerConexion();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validarUsuario(String email, String password) {

        String sql = "SELECT * FROM registro WHERE email = ? AND password = ?";

        try {
            //obtenemos la conexion
            Connection conexion = ConectionDb.obtenerConexion();
            //preparar la consulta
            PreparedStatement consulta = conexion.prepareStatement(sql);
            //argumentos
            consulta.setString(1, email);
            consulta.setString(2, password);
            //ejecutar la consulta
            ResultSet resultado = consulta.executeQuery();
            
            boolean validar = resultado.next();
            return validar;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
