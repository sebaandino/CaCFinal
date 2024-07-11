package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.coneccion.ConectionDb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!password.equals(confirmPassword)) {
            out.println("<h2>Las contraseñas no coinciden.</h2>");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConectionDb.obtenerConexion();
            String sql = "INSERT INTO registro (email, username, password) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.executeUpdate();

            out.println("<h2>Usuario registrado exitosamente!</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Ocurrió un error al registrar el usuario.</h2>");
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.html");
    }
}