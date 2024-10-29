<%-- 
    Document   : orderdetail
    Created on : Jul 1, 2024, 10:34:38 AM
    Author     : DELL
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
    </head>
    <body>
        <h2 style="color:green">
            Order Successfully! Please go back to your book store
        </h2>
        <c:if test="${not empty sessionScope}">
            <c:set var="cart" value="${requestScope.ORDER_DETAIL}" />
            <c:if test="${not empty cart}">
                <form action="DispatchServlet" method="POST">
                    <table border ="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>SKU</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                
                            </tr>
                        </thead>
                        <tbody>


                            <c:forEach var = "current" items = "${sessionScope.CART.getItems()}" varStatus = "status">
                                <tr>
                                    <td>
                                        ${status.count}
                                    </td>
                                    <td>

                                        ${current.getValue().getSku()}
                                    </td>
                                    <td>

                                        ${current.getValue().getName()}
                                    </td>
                                    <td>

                                        ${current.getValue().getDescription()}
                                    </td>
                                    <td>

                                        ${current.getValue().getQuantity()}
                                    </td>
                                    <td>

                                        ${current.getValue().getPrice()}
                                    </td>
                                    <c:set var="total" value="${current.getValue().getQuantity() * current.getValue().getPrice()}"/>
                                    <c:set var="alltotal" value="${alltotal + total}"/>

                                    

                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </form>
            </c:if>
        </c:if>

        <form action="DispatchServlet" method="POST">
            <input type="submit" value="Go Back To Book Strore" name="btAction" />
        </form>

    </body>
</html>
