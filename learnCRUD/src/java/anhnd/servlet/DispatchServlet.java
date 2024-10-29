/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PHUCHAU
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String VIEW_CART_PGAE = "viewCart.jsp";
    private final String CHECKOUT_PAGE = "checkout.jsp";
    private final String LOGIN_CONTROLLER = "LoginServlet";//url pattern trong web.xml
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastName";
    private final String LOGOUT_CONTROLLER = "logoutServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String STARTUP_CONTROLLER = "StartUpServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "remoceItFromCartServlet";
    private final String CHECK_OUT_FROM_CART_ORDER_CONTROLLER = "CheckOutServlet";
    private final String SHOW_BOOK_CONTROLLER = "showBookServlet";
    private final String CREATE_ACCOUNT_CONTROLLER = "AddAccountServlet";

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
        // dinh nghia ng dung da click nut gi
        String button = request.getParameter("btAction");// copy thg nay qua may cho khac de su dung 
        String url = LOGIN_PAGE;
        try {
            if (button == null) {// truong hop lan dau tien
                //check cookie

            } else if (button.equals("Login")) {//buoc 2 mapping vao trong dieu phoi
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (button.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (button.equals("delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Add Book to Your Cart")) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (button.equals("View your Cart")) {
                url = VIEW_CART_PGAE;
            } else if (button.equals("Remove Select Item")) {
                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
            } else if (button.equals("Check Out")) {
                url = CHECKOUT_PAGE;
            } else if (button.equals("Check Out Now")) {
                url = CHECK_OUT_FROM_CART_ORDER_CONTROLLER;
            } else if (button.equals("Go Back To Book Strore")) {
                url = SHOW_BOOK_CONTROLLER;
            } else if (button.equals("Create Account")) {
                url = CREATE_ACCOUNT_CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
