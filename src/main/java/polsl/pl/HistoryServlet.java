/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package polsl.pl;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import polsl.pl.model.History;
import polsl.pl.model.HistoryEntry;

/**
 * View user's history. Users can only see this session's {@link polsl.pl.model.History history}
 * 
 * @see polsl.pl.model.History
 * 
 * @author luiz
 * @version 1.0
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class HistoryServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            
            Object userHistory = session.getAttribute("history");
            History cipherHistory = (History) userHistory;
            if (userHistory == null) {
                cipherHistory = new History();
            }else {
                History existingHistory = (History) userHistory;
                cipherHistory = new History(existingHistory.getHistory());
            }
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>History</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.println("<style>table, th, td {border:1px solid black;}</style>");
            out.println(String.format("<a style='color:black' href=%s><-Home</a>", request.getContextPath() + "/"));
            out.println("<h1>History</h1>");
            
            out.println("<table style='width: 50%'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Action</th>");
            out.println("<th>Text</th>");
            out.println("<th>Key</th>");
            out.println("<th>Result</th>");
            out.println("</tr>");
            out.println("</thead>");
            
            out.println("<tbody>");
            for(HistoryEntry entry : cipherHistory.getHistory()) {
                out.println("<tr>");
                out.println(String.format("<td>%s</td>", entry.option));
                out.println(String.format("<td>%s</td>", entry.text));
                out.println(String.format("<td>%s</td>", entry.key));
                out.println(String.format("<td>%s</td>", entry.result));
                out.println("</tr>");
            }
            out.println("</tbody>");
            
            out.println("</table>");
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
