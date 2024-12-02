/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package fr.devavance.calculatrice.exceptions;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author BL
 */
@WebServlet(name = "ErrorHandler", urlPatterns = {"/error-handler"})
public class ErrorHandler extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request,
	  HttpServletResponse response) 
                throws ServletException, IOException {
		
            processError(request, response);
	}


    public void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Inconnu";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Inconnu";
        }

        request.setAttribute("statusCode", statusCode);
        request.setAttribute("requestUri", requestUri);
        request.setAttribute("servletName", servletName);
        request.setAttribute("exception", throwable);

        RequestDispatcher dispatcher;
        dispatcher = getServletContext().getRequestDispatcher("/error_backend.html");
        dispatcher.forward(request, response);
    }

}