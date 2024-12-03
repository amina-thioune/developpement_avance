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
import java.io.PrintWriter;

@WebServlet(name = "UtilisateurNonAutorise", urlPatterns = {"/utilisateurNonAutorise"})
public class UtilisateurNonAutorise extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Vous n'êtes pas connectés, votre login et/ou votre mot de passe sont incorrects.</p>");
        out.println("<a href='login.html'>Retour au formulaire d'authentification</a>");
    }
}

