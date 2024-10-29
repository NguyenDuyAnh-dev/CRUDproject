/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import anhnd.register.RegisterDAO;

/**
 *
 * @author PHUCHAU
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {
    private final String UPDATE_ERROR_PAGE = "updateErrorAccountPage.html";
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
        
        String urlRewriting = UPDATE_ERROR_PAGE;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String isAdmin = request.getParameter("chkAdim");
            boolean role = false;
            if (isAdmin != null) {
                role = true;
            }
            String searchValue = request.getParameter("lastSearchValue");
            
            RegisterDAO dao = new RegisterDAO();
            boolean result = dao.updateAccount(username, password, role);
            
            if(result){
                urlRewriting = "DispatchServlet?btAction=Search&txtSearch="
                        + searchValue;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log("AddAccountServlet_SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            log("AddAccountServlet_ClassNotFound" + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
