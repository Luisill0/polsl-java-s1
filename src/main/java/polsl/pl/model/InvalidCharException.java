/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polsl.pl.model;

/**
 * Exception raised if one of the values in a String is not in defined within an alphabet
 * 
 * @author luiz
 * @version 1.0
 */
public class InvalidCharException extends Exception {
    public InvalidCharException(String errorMessage) {
        super(errorMessage);
    }
}