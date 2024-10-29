<%-- 
    Document   : createAccount
    Created on : 28-06-2024, 08:26:31
    Author     : PHUCHAU
--%>

<%@page import="anhnd.registration.RegistrationCreateError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}" />

            Username: <input type="text" name="txtUsername" value="${param.txtUsername}"/><br/>
            <c:if test="${not empty errors.usenameLengthErr}">
                <font color="red">${errors.usenameLengthErr}</font><br/>
            </c:if>
            <c:if test="${not empty errors.usenameIsExisted}">
                <font color="red">${errors.usenameIsExisted}</font><br/>
            </c:if>
            Password: <input type="password" name="txtPassword" value=""/><br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">${errors.passwordLengthErr}</font><br/>
            </c:if>
            Confirm: <input type="password" name="txtConfirm" value=""/><br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">${errors.confirmNotMatch}</font><br/>
            </c:if>
            Full name: <input type="text" name="txtFullname" value="${param.txtFullname}"/><br/>
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color="red">${errors.fullnameLengthErr}</font><br/>
            </c:if>
            <input type="submit" value="Create Account" name="btAction"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>

