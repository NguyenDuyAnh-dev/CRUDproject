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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import anhnd.register.RegisterCreateError;
import anhnd.register.RegisterDTO;
import anhnd.register.RegisterDAO;

/**
 *
 * @author PHUCHAU
 */
@WebServlet(name = "AddAccountServlet", urlPatterns = {"/AddAccountServlet"})
public class AddAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";
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

        //1 get all user info
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        boolean foundErr = false;
        RegisterCreateError errors = new RegisterCreateError();

        String url = ERROR_PAGE;
        try {
            // 4 loi ng dung va 1 loi he thong 
            //2 check tat ca loi cua ng dung
            if (username.trim().length() < 6 || username.trim().length() > 12) {
                foundErr = true;
                errors.setUsenameLengthErr("User name lenghth 6 -> 12");
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthErr("Password lenghth 6 -> 20");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatch("Confirm must match password");
            }
            if (fullname.trim().length() < 6 || fullname.trim().length() > 25) {
                foundErr = true;
                errors.setFullnameLengthErr("Full name lenghth 6 -> 25");
            }
            if (foundErr) {//errors occur
                request.setAttribute("CREATE_ERRORS", errors);
            } else {//no error
                //3 call method DAO
                RegisterDAO dao = new RegisterDAO();
                RegisterDTO dto = new RegisterDTO(username, password, fullname, false);
                boolean result = dao.createAccount(dto);
                //4 su li ket qua
                if(result){
                    url = LOGIN_PAGE;
                }
            }

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("AddAccountServlet_SQL" + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setUsenameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }//end process username is existed in DB
        } catch (ClassNotFoundException ex) {
            log("AddAccountServlet_ClassNotFound" + ex.getMessage());
        }  finally {
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
