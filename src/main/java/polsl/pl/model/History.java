/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polsl.pl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to store history of encodings and decodings in the application.
 * ArrayList that stores entries
 * 
 * @author luiz
 * @version 2.0
 */

public class History {
    private final List<HistoryEntry> history;
    
    public History() {
        this.history = new ArrayList();
    }
    
    public History(List<HistoryEntry> existingHistory) {
        this.history = existingHistory;
    }
    
    /**
     * Get the list of entries in the history
     * 
     * @return List of entries in history
     */
    public List<HistoryEntry> getHistory() {
        return this.history;
    }

    /**
     * Adds new entry to the history of cipher operations
     * 
     * @param entry. History entry object 
     *   
     * @see HistoryEntry
     * @version 2.0
     * @author luiz
     */
    public void pushHistory(HistoryEntry entry) {
        this.history.add(entry);
    }
}