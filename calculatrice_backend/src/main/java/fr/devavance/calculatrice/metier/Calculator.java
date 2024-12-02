/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.calculatrice.metier;

/**
 *
 * @author kn_33
 */
public class Calculator {
    
    public double addition(String operande_1, String operande_2){
        double operande1 = Double.parseDouble(operande_1) ;        
        double operande2 = Double.parseDouble(operande_2) ;
        return operande1 + operande2 ;
    }
    
    public double soustraction(String operande_1, String operande_2){
        double operande1 = Double.parseDouble(operande_1) ;        
        double operande2 = Double.parseDouble(operande_2) ;
        return operande1 - operande2 ;
    }
    
    public double multiplication(String operande_1, String operande_2){
        double operande1 = Double.parseDouble(operande_1) ;        
        double operande2 = Double.parseDouble(operande_2) ;
        return operande1 * operande2 ;
    }
    
    public double division(String operande_1, String operande_2){
        double operande1 = Double.parseDouble(operande_1) ;        
        double operande2 = Double.parseDouble(operande_2) ;
        return operande1 / operande2 ;
    }
}
