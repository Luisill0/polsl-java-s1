/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package polsl.pl;

import polsl.pl.model.Cipher;
import polsl.pl.model.InvalidCharException;
import polsl.pl.model.Redirects;
import polsl.pl.model.History;
import polsl.pl.model.HistoryEntry;
import polsl.pl.model.CipherOption;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Text operations (encode/decode) handler.
 * Redirects to /Error if an error is encountered
 * 
 * @see ErrorServlet
 * @see InvalidCharException
 * 
 * @author luiz
 * @version 2.0
 */
@WebServlet(name = "Text", urlPatterns = {"/Text"})
@Redirects(to="/Error")
public class TextServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Cookie[] cookies = request.getCookies();
            HttpSession session = request.getSession();
            
            Cipher cipher = new Cipher();
            
            CipherOption textAction = null;
            
            try {
                String actionString = request.getParameter("textAction");
                System.out.println(actionString);
                textAction = CipherOption.valueOf(actionString);
            } catch (IllegalArgumentException e) {
                Cookie errorCookie =  this.incrementCookie(cookies, "errors");
                response.addCookie(errorCookie);
                
                session.setAttribute("errorMessage", e.getMessage());
                response.sendRedirect(request.getContextPath() + "/Error");
                return;
            }
            
            String text = request.getParameter("text");
            String key = request.getParameter("key");
            
            String result = "";
            
            Cookie visitCookie = new Cookie("lastVisit", new java.util.Date().toString().replace(' ','_'));
            visitCookie.setMaxAge(3600*24*30);
            response.addCookie(visitCookie);
            
            Cookie numTextActionsCookie = this.incrementCookie(cookies,"numTextActions");
            response.addCookie(numTextActionsCookie);
            
            try {
                result = textAction == CipherOption.ENCODE ? cipher.encode(text, key) : cipher.decode(text, key);
            } catch(InvalidCharException e){
                Cookie errorCookie =  this.incrementCookie(cookies, "errors");
                response.addCookie(errorCookie);
                
                session.setAttribute("errorMessage", e.getMessage());
                response.sendRedirect(request.getContextPath() + "/Error");
                return;
            }
            
            Object userHistory = session.getAttribute("history");
            History cipherHistory = null;
            if (userHistory == null) {
                cipherHistory = new History();
            }else {
                History existingHistory = (History) userHistory;
                cipherHistory = new History(existingHistory.getHistory());
            }
            cipherHistory.pushHistory(new HistoryEntry(textAction, text, key, result));
            session.setAttribute("history", cipherHistory);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println(String.format("<title>%s</title>", textAction));            
            out.println("</head>");
            out.println("<body>");
            out.println(String.format("<a style='color:black' href=%s><-Home</a>", request.getContextPath() + "/"));
            out.println(String.format("<div style='margin-top:2rem'>Text: %s </div>", text));
            out.println(String.format("<div>Key: %s </div>", key));
            out.println(String.format("<div>Result: %s </div>", result));
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private Cookie incrementCookie(Cookie[] cookies, String cookieName) {
        int counterValue = 0;
        if (cookies != null) {
            for(Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    counterValue = Integer.parseInt(cookie.getValue()) + 1;
                }
            }
        }
        Cookie cookie = new Cookie(cookieName, String.valueOf(counterValue));
        cookie.setMaxAge(3600*24*30);
        return cookie;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
