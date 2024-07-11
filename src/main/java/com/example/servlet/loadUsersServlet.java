package com.example.servlet;

import java.io.IOException;
import java.util.List;

import com.example.dao.UsuarioDao;
import com.example.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loadUsers")
public class loadUsersServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDao UsuarioDao = new UsuarioDao();
        List<UserEntity> usuarios = UsuarioDao.getAllUsers(); 

        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), usuarios);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("gestionUsuarios.html");
    }



}
