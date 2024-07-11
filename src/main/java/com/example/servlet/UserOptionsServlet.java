package com.example.servlet;

import java.io.IOException;

import com.example.dao.UsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userOptions")
public class UserOptionsServlet extends HttpServlet{

    @Override
    public void init() throws ServletException {
        // Inicialización de recursos
        System.out.println("Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idEliminar = Integer.parseInt(request.getParameter("id"));
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.eliminar(idEliminar);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\": \"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{\"status\": \"error\"}");
        }
    }

}
