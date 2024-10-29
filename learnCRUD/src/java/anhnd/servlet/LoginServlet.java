/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import anhnd.register.RegisterDTO;
import anhnd.register.RegisterDAO;

/**
 *
 * @author PHUCHAU
 */
public class LoginServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    private final String INVALID_PAGE = "invalid.html";

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
        PrintWriter out = response.getWriter();

        
        String url = INVALID_PAGE;
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        try {
            
                //1.get all user's info

                //2. Call method of model
                //2.1 NEW DAO Object
                RegisterDAO dao = new RegisterDAO();
                //2.2 Call method of DAO
                //boolean result = dao.checkLogin(user, pass);
                RegisterDTO result = dao.checkLogin(user, pass);
                //3.Process result
                if (result != null) {
                    url = SEARCH_PAGE;
                    //open session
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", result);
                    session.setAttribute("USERNAME", user);
                    //sending cookies
//                    Cookie cookie = new Cookie(user, url);
//                    cookie.setMaxAge(60 * 30);
//                    response.addCookie(cookie);
                }
            //user click login button
        } catch (SQLException ex) {
            ex.printStackTrace();
            log("AddAccountServlet_SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            log("AddAccountServlet_ClassNotFound" + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
