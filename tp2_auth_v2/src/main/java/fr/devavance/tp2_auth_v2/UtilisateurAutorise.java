/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.tp2_auth_v2;

/**
 *
 * @author amina
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UtilisateurAutorise", urlPatterns = {"/utilisateurAutorise"})
public class UtilisateurAutorise extends HttpServlet {
  @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login"); 
        String section = (String) request.getAttribute("section"); 

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Bonjour " + login + "</p>");
        out.println("<p>Vous êtes dans la section " + section + "</p>");
    }
}

