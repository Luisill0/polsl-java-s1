/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polsl.pl.model;

/**
 * Options the user can choose for the application
 * 
 * @see CipherOption#ENCODE
 * @see CipherOption#DECODE
 * 
 * @author luiz
 * @version 2.0
 */
public enum CipherOption {
    /**
     * Encode plain text
     * 
    */
    ENCODE("Encode"),
    
    /**
     * Decode encoded text
    */
    DECODE("Decode");
    
    private final String option;
    
    private CipherOption(String option){
        this.option = option;
    }
    
    @Override
    public String toString() {
       return this.option;
    }
}
