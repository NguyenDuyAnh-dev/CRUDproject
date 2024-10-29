<%-- 
    Document   : checkout
    Created on : 03-07-2024, 14:09:24
    Author     : PHUCHAU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="DispatchServlet" method="POST">
            <p>
                Total: ${param.alltotal}
                <input type="hidden" name="alltotal" value="${param.alltotal}"/>
            </p>
            <label for="nameCustomer">
                Name:
                <input type="text" name="nameOfCustomer" value="" id="nameCustomer"/>
            </label></br>
            <label for="email">
                Email:
                <input type="email" name="emailOfCustomer" value="" id="email"/>
            </label></br>
            <label for="adrress">
                Address:
                <textarea id="adrress" name="addressOfCustomer"  rows="4" cols="20">
                            
                </textarea>
            </label></br>
            <input type="submit" value="Check Out Now" name="btAction"/>
        </form>
    </body>
</html>
