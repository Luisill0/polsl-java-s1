/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package polsl.pl;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author luiz
 * @version 1.0
 */
@WebServlet(name = "Stats", urlPatterns = {"/Stats"})
public class StatsServlet extends HttpServlet {

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
            
            String lastVisit = "never";
            int errors = 0;
            int actions = 0;
            
            for(Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "lastVisit" -> {
                        lastVisit = cookie.getValue();
                    }
                    case "errors" -> {
                        errors = Integer.parseInt(cookie.getValue());
                    }
                    case "numTextActions" -> {
                        actions = Integer.parseInt(cookie.getValue());
                    }
                }
            }
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StatsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println(String.format("<a style='color:black' href=%s><-Home</a>", request.getContextPath() + "/"));
            out.println("<h1>Stats</h1>");
            
            out.println(String.format("<p><strong>Last visit: </strong>%s</p>", lastVisit));
            out.println(String.format("<p><strong>Number of errors: </strong>%d</p>", errors));
            out.println(String.format("<p><strong>Number of texts encoded/decoded: </strong>%d</p>", actions));
            
            out.println("</body>");
            out.println("</html>");
        }
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
