/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlet;

import anhnd.cart.CartBean;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import anhnd.tblProduct.TblProductDAO;
import anhnd.tblProduct.TblProductDTO;

/**
 *
 * @author PHUCHAU
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final String ERROR_PAGE = "errorPage.jsp";
    private final String SHOW_BOOK_CONTROLLER = "showBookServlet";

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

        String url = SHOW_BOOK_CONTROLLER;

        try {
            //1  khanh hang den cho lay gio
            HttpSession session = request.getSession(true);
            //2. khach hang lay gio
            
            CartBean cart = (CartBean) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartBean();
            }
            int sku = Integer.parseInt(request.getParameter("sku"));
            String name = request.getParameter("name");
            float price = Float.parseFloat(request.getParameter("price"));
            String description = request.getParameter("description");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            TblProductDTO product = new TblProductDTO(sku, name, description, quantity, price, true);
            //3. customer drops an item to cart
            cart.addItemToCart(product);
            quantity += 1;
            session.setAttribute("CART", cart);

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("AddAccountServlet_SQL" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("AddAccountServlet_ClassNotFound" + ex.getMessage());
        } finally {
            //4. quay lai buoc 3
            // dung req hay res nao cung dc
            response.sendRedirect(url);

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
