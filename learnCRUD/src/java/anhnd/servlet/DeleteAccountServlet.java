/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {
private final String ERROR_PAGE = "errorPage.jsp";

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
        //1 get all user inf
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        String url = ERROR_PAGE;
        try {
            //2new DAO object
            RegisterDAO dao = new RegisterDAO();
            //2.1call method of DAO
            boolean result = dao.deleteAcount(username);
            //3process result
            if (result) {
                //referese bang calling the chuc nang truoc do
                url = "DispatchServlet"
                    + "?btAction=Search"
                    + "&txtSearch=" + searchValue;
            }//user is authenticated
            //user
        } catch (SQLException ex) {
            ex.printStackTrace();
            log("AddAccountServlet_SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            log("AddAccountServlet_ClassNotFound" + ex.getMessage());
        } finally {
           response.sendRedirect(url);//sd request se ko phan biet dc search vs deletle vi co 2 parameter se tao thanh mang va lay thang dau tien => ko phan biet dc thg nao trc
            
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
