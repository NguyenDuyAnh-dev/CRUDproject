<%-- 
    Document   : market
    Created on : 21-06-2024, 06:44:03
    Author     : PHUCHAU
--%>

<%-- <%@page import="product.ProductDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Book</h1>
        <%--
        <%
            List<ProductDTO> booklist = (List<ProductDTO>) request.getAttribute("BOOK_LIST");
            if (booklist != null) {
        %>--%>
        <c:set var="booklist" value="${requestScope.BOOK_LIST}" />
        <c:if test="${not empty booklist}" >
            <form action="DispatchServlet" method="POST">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>SKU</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Add to Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="count" value="0" />
                        <c:forEach var="product" items="${booklist}" varStatus="counter" >
                        <form action="DispatchServlet" method="POST" >
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${product.sku}
                                    <input type="hidden" name="sku" value="${product.getSku()}" />
                                </td>
                                <td>
                                    ${product.name}
                                    <input type="hidden" name="name" value="${product.getName()}" />
                                </td>
                                <td>
                                    ${product.price}
                                    <input type="hidden" name="price" value="${product.getPrice()}" />
                                </td>
                                <td>
                                    ${product.description}
                                    <input type="hidden" name="description" value="${product.getDescription()}" />
                                </td>

                            <input type="hidden" name="quantity" value="1" />

                            <td>
                                <input type="submit" value="Add Book to Your Cart" name="btAction" />
                                <input type="hidden" name="sku"
                                       value="${product.getSku()}" />
                            </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                    <input type="submit" value="View your Cart" name="btAction" />
            </form>
        </c:if>
        <a>    </a><a href="login.html">click here to go out</a>

        <%--<tbody>
            <%
                int count = 0;
                for (ProductDTO product : booklist) {
            %>

            <form action="DispatchServlet" method="POST" >
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= product.getSku()%>
                        <input type="hidden" name="sku" value="<%= product.getSku()%>" />
                    </td>
                    <td>
                        <%= product.getName()%>
                        <input type="hidden" name="name" value="<%= product.getName() %>" />
                    </td>
                    <td>
                        <%= product.getPrice()%>
                        <input type="hidden" name="price" value="<%= product.getPrice() %>" />
                    </td>
                    <td>
                        <%= product.getDescription()%>
                        <input type="hidden" name="description" value="<%= product.getDescription() %>" />
                    </td>
                    
                        <input type="hidden" name="quantity" value="1" />
                    
                    <td>
                        <input type="submit" value="Add Book to Your Cart" name="btAction" />
                        <input type="hidden" name="sku"
                                       value="<%= product.getSku() %>" />
                    </td>
                </tr>
            </form>


            <%
                }
            %>

            <%
                }
            %>


            <!-- Buttons for submitting the form with different actions -->
        --%>


    </body>
</html>
