<%-- 
    Document   : viewCart
    Created on : 18-06-2024, 08:52:40
    Author     : PHUCHAU
--%>

<%--<%@page import="product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="cart.CartBean"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart Includes</h1>
        <%--<%
            //1.customer goes to cart her/his place
            if (session != null) {
                //2.customre take her/his cart
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    //3. customer get items
                    Map<Integer, ProductDTO> items = cart.getItems();
                    if (items != null) {
                        //4.Cust shows all item
        %>--%>

        <c:if test="${not empty sessionScope}">
            <c:set var="cart" value="${sessionScope.CART}" />
            <c:if test="${not empty cart}">
                <c:set var="items" value="${sessionScope.CART.getItems()}"/>
                <c:if test="${not empty items}">
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
                                    <th>Action</th>
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

                                        <td>
                                            <input type="checkbox" name="chkItem" value="${current.getKey()}"/>
                                        </td>

                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="2">
                                        <a href="showBookServlet">add more to your cart</a>
                                    </td>
                                    <td colspan="2">
                                        <input type="submit" value="Remove Select Item" name="btAction" />
                                    </td>
                                    <td colspan="1">
                                        <p>
                                            ${alltotal}
                                            <input type="hidden" name="alltotal" value="${alltotal}"/>
                                        </p>
                                    </td>
                                    <td colspan="2">
                                        <input type="submit" value="Check Out" name="btAction"/>
                                    </td>
                                    </form>
                                </tr>
                            </tbody>
                        </table>
                    </c:if>
                </c:if>
            </c:if>

            <c:if test="${empty items}">
                <div class="no-item row">
                    <h2>
                        <font color="red">
                        No item exited in your cart!
                        </font></br>
                        <a href="showBookServlet">click here to go back book store</a></br>
                    </h2>

                </div>
            </c:if>

            <c:if test="${empty cart}">
                <h2>
                    <font color="red">
                    No cart exited!!!
                    </font></br>
                    <a href="showBookServlet">click here to go back book store</a></br>
                </h2>
            </c:if>
                        

            <%--<table border ="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SKU</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (Map.Entry<Integer, ProductDTO> entry : items.entrySet()) {
                            ProductDTO dto = entry.getValue();
                            int quantity = items.get(dto.getSku()).getQuantity();
                    %>
                <form action="DispatchServlet">
                    <c:forEach var = "current" items = "${sessionScope.CART.getItems()}" varStatus = "status">
                        <tr>
                            <td>
                                <%= ++count%>
                            </td>
                            <!--
                            <td>
                            <%= dto.getSku()%>
                            <input type="hidden" name="sku" value="<%= dto.getSku()%>"/>
                        </td>
                        <td>
                            <%= dto.getName()%>
                            <input type="hidden" name="name" value="<%= dto.getName()%>"/>
                        </td>
                        <td>
                            <%= dto.getDescription()%>
                            <input type="hidden" name="description" value="<%= dto.getDescription()%>"/>
                        </td>
                        <td>
                            <%= quantity%>
                            <input type="hidden" name="quantity" value="<%= quantity%>"/>
                        </td>
                        <td>
                            <%= dto.getPrice()%>
                            <input type="hidden" name="price" value="<%= dto.getPrice()%>"/>
                        </td>
                            -->
                            <td>
                                <c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                    <c:if test = "${current.getKey() == details.getSku()}"> 
                                        ${details.sku()}
                                    </c:if> 
                                </c:forEach>

                        </td>
                        <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                <c:if test = "${current.getKey() == details.getSku()}"> 
                                    ${details.name()}
                                </c:if> 
                            </c:forEach></td>
                        <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                <c:if test = "${current.getKey() == details.getSku()}"> 
                                    ${details.description()}
                                </c:if> 
                            </c:forEach></td>
                        <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                <c:if test = "${current.getKey() == details.getSku()}"> 
                                    ${quantity}
                                </c:if> 
                            </c:forEach></td>
                        <td><c:forEach var = "details" items = "${sessionScope.CartDetails}" >
                                <c:if test = "${current.getKey() == details.getSku()}"> 
                                    ${details.price()}
                                </c:if> 
                            </c:forEach></td>
                        <td>
                            <input type="checkbox" name="chkItem" value="${product.getSku()}"/>
                        </td>
                    </tr>
                    <%
                        }//each entry is process
                    %>
                    <tr>
                        <td colspan="5">
                            <a href="showBookServlet">add more to your cart</a>
                        </td>
                        <td colspan="2">
                            <input type="submit" value="Remove Select Item" name="btAction" />
                        </td>

                    </tr>
                    </tbody>
            </table>
            <label for="nameCustomer">
                Name:
                <input type="text" name="nameOfCustomer" id="nameCustomer"/>
            </label></br>
            <label for="email">
                Email:
                <input type="email" name="emailOfCustomer" id="email"/>
            </label></br>
            <label for="adrress">
                Address:
                <textarea id="adrress" name="addressOfCustomer" rows="4" cols="20">
                            
                </textarea>
            </label></br>
            <input type="submit" value="Check Out" name="btAction"/>
        </form>
        <%                        return;
            }// items has exited

        }// cart has exited

        }// cart place has exited

        %>

        <h2>
            <font color="red">
            No cart exited!!!
            </font>
        </h2>
            --%>
    </body>
</html>
