/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlet;

import anhnd.cart.CartBean;
import anhnd.tblProduct.TblProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import anhnd.tblProduct.TblProductDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PHUCHAU
 */
@WebServlet(name = "remoceItFromCartServlet", urlPatterns = {"/remoceItFromCartServlet"})
public class remoceItFromCartServlet extends HttpServlet {

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
        try {

            //1.customer go to his/ her cart
            HttpSession session = request.getSession(false);// check xem session con ton tai hay ko sd false
            if (session != null) {
                //2.customer take his/her cart
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    //3.customer gets items
                    Map<Integer, TblProductDTO> items = cart.getItems();
                    if (items != null) {
                        //4.customer choose all item to remove
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {// phai chonn it nhat 1 item
                            for (String item : selectedItems) {
                                cart.RemoveItemToCart(Integer.parseInt(item));
                            }//xu lis tung item
                            session.setAttribute("CART", cart);
                        }
                    }//items have existed
                }//cart has exited
            }// carts place exitsed
        }finally {
            //5.referese goi lai chuc nang trc do( view cart) sd url rewriting
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View your Cart";
            //btAction bi trug noen sd response
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
