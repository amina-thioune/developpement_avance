package fr.devavance.calculatrice.controller;

import fr.devavance.calculatrice.metier.Calculator;
import fr.devavance.calculatrice.exeptions.CalculatorExceptionHandler;
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
        } catch (CalculatorExceptionHandler | IllegalArgumentException e) {
            
            displayError(response, e.getMessage());
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

   
    private void checkParameters(ArrayList<String> operation) throws CalculatorExceptionHandler {
        if (operation.get(1) == null || operation.get(2) == null) {
            throw new CalculatorExceptionHandler("Les opérandes ne doivent pas être nulles");
        }

        if (!operation.get(2).matches("[0-9]+") || !operation.get(2).matches("[0-9]+")){
            throw new CalculatorExceptionHandler("Les opérandes ne doivent pas être que des nombres reels");
       }    

        if (operation.get(0).equals("div") && Double.parseDouble(operation.get(2)) == 0) {
            throw new CalculatorExceptionHandler("Le dénominateur ne doit pas être nul pour une division");
        }
    }

        
    private double proceedCalculate(ArrayList<String> operation) throws CalculatorExceptionHandler {
        Calculator calculator = new Calculator();

        if (operation.get(0).equals("add")) {
            return calculator.addition(operation.get(1), operation.get(2));
        } else if (operation.get(0).equals("sub")) {
            return calculator.soustraction(operation.get(1), operation.get(2));
        } else if (operation.get(0).equals("multi")) {
            return calculator.multiplication(operation.get(1), operation.get(2));
        } else if (operation.get(0).equals("div")) {
            return calculator.division(operation.get(1), operation.get(2));
        } else {
            throw new CalculatorExceptionHandler("Opération non reconnue");
        }

    }

    
    private void viewCalculate(HttpServletResponse response, ArrayList operation, double result) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calculatrice</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Valeur de l'opérande 1 : " + operation.get(1) + "</p>");
        out.println("<p>Valeur de l'opérande 2 : " + operation.get(2) + "</p>");
        out.println("<p>Opération demandée : " + operation + "</p>");
        out.println("<p>Résultat : " + result + "</p>");
        out.println("</body>");
        out.println("</html>");
    }

    
    private void displayError(HttpServletResponse response, String message) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Erreur</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Erreur : " + message + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
