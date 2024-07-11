package com.example.servlet;

import java.io.IOException;
import com.example.dao.UsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UsuarioDao UsuarioDao = new UsuarioDao();
        boolean usuarioValido = UsuarioDao.validarUsuario(email, password);

        if (usuarioValido) {
            response.sendRedirect("intro.html");
        } else {
            response.sendRedirect("index.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }

}
