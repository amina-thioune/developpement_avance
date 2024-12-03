/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.tp2_auth_v2;

/**
 *
 * @author amina
 */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "Identification", urlPatterns = {"/auth"})
public class Identification extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String section = request.getParameter("section"); 

        if ("admin".equals(login) && "admin".equals(password)) {
            request.setAttribute("section", section); 
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/utilisateurAutorise");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/utilisateurNonAutorise");
            rd.forward(request, response);
        }
    }
}

