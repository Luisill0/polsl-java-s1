/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polsl.pl.model;


import java.util.*;

/**
 * Implementation of the Vigenere cipher
 * 
 * @author luiz
 * @version 2.0
 */
public class Cipher {
    private final ArrayList<String> alphabet = new ArrayList<>(Arrays.asList(
        "a","b","c","d","e","f","g",
        "h","i","j","k","l","m","n",
        "o","p","q","r","s","t","u",
        "v","w","x","y","z"));
    
    /**
     * Returns an encoded String object using the Vigenere cipher
     * 
     * @param plaintext The text to be encoded
     * @param key The key that will be used to encode the text
     * @return String encoded
     * @throws InvalidCharException If a character of either the text or key are not in the 26-letter alphabet
     * @see <a href="https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher">Vigenere Cipher</a>
    */
    public String encode(String plaintext, String key) throws InvalidCharException {
        int keyIndex = 0;
        ArrayList<String> ciphered = new ArrayList<>();
        
        for (char letterOfPlaintext : plaintext.toCharArray()) {
            if (letterOfPlaintext == ' ') {
                ciphered.add(" ");
                continue;
            }
            
            int alphabetPlaintextIndex = alphabet.indexOf(String.valueOf(letterOfPlaintext));
            if (alphabetPlaintextIndex < 0) 
                throw new InvalidCharException(String.format(
                    "invalid character in text (%c)", 
                    letterOfPlaintext
                ));
            
            char letterOfKey = key.charAt(keyIndex);
            int alphabetKeyIndex = alphabet.indexOf(String.valueOf(letterOfKey));
            if (alphabetKeyIndex < 0)
                throw new InvalidCharException(String.format(
                    "invalid character in key (%c)",
                    letterOfKey
                ));
            
            ciphered.add( alphabet.get((alphabetKeyIndex + alphabetPlaintextIndex) % 26) );
            keyIndex = keyIndex >= key.length()-1 ? 0 : keyIndex+1;
        }

        return String.join("", ciphered);
    }
    
    /**
     * Returns a decoded String object using the Vigenere cipher
     * 
     * @param encodedText The text to be decoded
     * @param key The key that was used to encode the text
     * @return String plain text decoded
     * @throws InvalidCharException If a character of either the text or key are not in the 26-letter alphabet
     * @see <a href="https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher">Vigenere Cipher</a>
    */
    public String decode(String encodedText, String key) throws InvalidCharException {
        int keyIndex = 0;
        ArrayList<String> ciphered = new ArrayList<>();

        for (char letterOfPlaintext : encodedText.toCharArray()) {
            if (letterOfPlaintext == ' ') {
                ciphered.add(" ");
                continue;
            }
            
            int alphabetPlaintextIndex = this.alphabet.indexOf(String.valueOf(letterOfPlaintext));
            if (alphabetPlaintextIndex < 0) 
                throw new InvalidCharException(String.format(
                    "invalid character in text (%c)", 
                    letterOfPlaintext
                ));
            
            char letterOfKey = key.charAt(keyIndex);
            int alphabetKeyIndex = this.alphabet.indexOf(String.valueOf(letterOfKey));
            if (alphabetKeyIndex < 0)
                throw new InvalidCharException(String.format(
                    "invalid character in key (%c)",
                    letterOfKey
                ));
            
            int substraction = alphabetPlaintextIndex - alphabetKeyIndex;
            int listIndex = substraction >= 0 ? substraction : 26 + substraction;
            
            ciphered.add( this.alphabet.get(listIndex));
            keyIndex = keyIndex >= key.length()-1 ? 0 : keyIndex+1;
        }

        return String.join("", ciphered);
    }
}
