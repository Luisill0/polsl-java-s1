/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polsl.pl.model;

/**
 * Class to save an entry to the history
 * 
 * @see HistoryEntry#option
 * @see HistoryEntry#text
 * @see HistoryEntry#key
 * @see HistoryEntry#result
 * 
 * @author luiz
 * @version 1.0
 */
public class HistoryEntry {
    /** CipherOption (encode/decode)
     * @see CipherOption
     */
    public final CipherOption option;
    /** Original text */
    public final String text;
    /** Key used to perform operation */
    public final String key;
    /** Result of the operation*/
    public final String result;
    
    public HistoryEntry(CipherOption option, String text, String key, String result) {
        this.option = option;
        this.text = text;
        this.key = key;
        this.result = result;
    }
    
    @Override
    public String toString() {
        return String.format(
            "{option: %s, text: %s, key: %s, result: %s}", 
            this.option, this.text, this.key, this.result
        );
    }
}
