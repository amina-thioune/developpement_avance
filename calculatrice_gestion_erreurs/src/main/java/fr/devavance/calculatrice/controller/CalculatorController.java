package fr.devavance.calculatrice.controller;

import fr.devavance.calculatrice.exceptions.ErrorHandler;
import fr.devavance.calculatrice.metier.Calculator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/calculate")
public class CalculatorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            ArrayList<String> operation = extractParameters(request);

            checkParameters(operation);

            double result = proceedCalculate(operation);

            viewCalculate(response, operation, result);
        } catch (Exception e) {
            request.setAttribute("javax.servlet.error.exception", e);
            request.setAttribute("javax.servlet.error.status_code", 500); 
            request.setAttribute("javax.servlet.error.servlet_name", "CalculatorController");
            response.sendRedirect("/error-handler");
    }
        }

    private ArrayList<String> extractParameters(HttpServletRequest request) {
        ArrayList<String> operation = new ArrayList<>();
        String operateur = request.getParameter("operation");
        String operande1 = request.getParameter("operande1");
        String operande2 = request.getParameter("operande2");

        operation.add(operateur);
        operation.add(operande1);
        operation.add(operande2);

        return operation;
    }

    private void checkParameters(ArrayList<String> operation) throws Exception {
        if (operation.get(1) == null || operation.get(2) == null) {
            throw new Exception("Les opérandes ne doivent pas être nulles");
        }

        try {
            Double.valueOf(operation.get(1));
            Double.valueOf(operation.get(2));
        } catch (NumberFormatException e) {
            throw new Exception("Les opérandes doivent être des nombres réels", e);
        }

        if ("div".equals(operation.get(0)) && Double.parseDouble(operation.get(2)) == 0) {
            throw new Exception("Le dénominateur ne doit pas être nul pour une division");
        }
    }

    private double proceedCalculate(ArrayList<String> operation) throws Exception {
        Calculator calculator = new Calculator();

        switch (operation.get(0)) {
            case "add":
                return calculator.addition(operation.get(1), operation.get(2));
            case "sub":
                return calculator.soustraction(operation.get(1), operation.get(2));
            case "multi":
                return calculator.multiplication(operation.get(1), operation.get(2));
            case "div":
                return calculator.division(operation.get(1), operation.get(2));
            default:
                throw new Exception("Opération non reconnue");
        }
    }

    private void viewCalculate(HttpServletResponse response, ArrayList<String> operation, double result) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calculatrice</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Valeur de l'opérande 1 : " + operation.get(1) + "</p>");
        out.println("<p>Valeur de l'opérande 2 : " + operation.get(2) + "</p>");
        out.println("<p>Opération demandée : " + operation.get(0) + "</p>");
        out.println("<p>Résultat : " + result + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
