/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.servlet;

import anhnd.cart.CartBean;
import anhnd.tblOrder.TblOrderDAO;
import anhnd.tblOrder.TblOrderDTO;
import anhnd.orderDetail.OrderDetailDAO;
import anhnd.orderDetail.OrderDetailDTO;
import anhnd.tblProduct.TblProductDAO;
import anhnd.tblProduct.TblProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PHUCHAU
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String ORDER_DETAIL_PAGE = "orderdetail.jsp";

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

        String url = "errorPage.jsp";
        String nameOfCustomer_raw = request.getParameter("nameOfCustomer");
        String email_raw = request.getParameter("emailOfCustomer");
        String address_raw = request.getParameter("addressOfCustomer");
        String total_raw = request.getParameter("alltotal");
        float total = 1;
        String nameOfCustomer = null;
        String email = null;
        String address = null;

        try {
            // order
            if (total_raw != null && !total_raw.isEmpty()) {
                total = Float.parseFloat(total_raw);
            }

            if (nameOfCustomer_raw != null && !nameOfCustomer_raw.isEmpty()) {
                nameOfCustomer = (String) nameOfCustomer_raw;
            }
            if (email_raw != null && !email_raw.isEmpty()) {
                email = (String) email_raw;
            }
            if (address_raw != null && !address_raw.isEmpty()) {
                address = (String) address_raw;
            }

            TblOrderDAO orderDAO = new TblOrderDAO();
            String orderID = orderDAO.getOrders();
            request.setAttribute("ORDER_ID", orderID);
            TblOrderDTO orderDTO = new TblOrderDTO(orderID, null, nameOfCustomer, address, email, total);

            boolean addOrder = orderDAO.createOrder(orderID, null, nameOfCustomer, address, email, total);

            // order detail
            HttpSession session = request.getSession(false);
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            if (session != null) {

                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {

                    Map<Integer, TblProductDTO> items = cart.getItems();
                    if (items != null) {

                        for (Map.Entry<Integer, TblProductDTO> product : items.entrySet()) {

                            float totalOrderDetail = 0;
                            int productID = product.getKey();
                            float unitPrice = product.getValue().getPrice();
                            int quantity = product.getValue().getQuantity();
                            totalOrderDetail += unitPrice * quantity;
                            String orderDetailId_raw = orderDetailDAO.getorderById();
                            int orderDetailId = Integer.parseInt(orderDetailId_raw);

                            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderDetailId, productID, unitPrice, quantity, orderID, totalOrderDetail);

                            //dang loi
//                            boolean addOrdeDetail = orderDetailDAO.addOrderDetail(orderDetailId, productID, unitPrice, quantity, orderID, totalOrderDetail);
                             // da fix dc
                            boolean addOrdeDetail = orderDetailDAO.addOrderDetail(orderDetailDTO);
                            if (!addOrdeDetail) {
                                throw new SQLException("Không thể thêm chi tiết đơn hàng cho sản phẩm ID: " + productID);
                            }
                            //update the table tbl_product
                            TblProductDAO productDAO = new TblProductDAO();
                            productDAO.updateProductQuantity(productID, quantity);
                            url = ORDER_DETAIL_PAGE;
                        }
                    }

                }

            }

            orderDetailDAO.showOrder(orderID);
            List<OrderDetailDTO> result = orderDetailDAO.getOrder();
            request.setAttribute("ORDER_DETAIL", result);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("ERROR", "Lỗi cơ sở dữ liệu: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            request.setAttribute("ERROR", "Lỗi không tìm thấy lớp: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            request.setAttribute("ERROR", "Lỗi định dạng số: " + ex.getMessage());
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
