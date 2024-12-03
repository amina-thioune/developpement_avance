/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.tp2_auth_v1;

/**
 *
 * @author amina
 */
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Identification", urlPatterns = {"/auth"})
public class Identification extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String login = request.getParameter("login");
    String password = request.getParameter("password");

    if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
        rd.forward(request, response);
        return;
    }

    if ("admin".equals(login) && "admin".equals(password)) {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login_succes.html");
        rd.forward(request, response);
    } else {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login_echec.html");
        rd.forward(request, response);
    }
}

}
