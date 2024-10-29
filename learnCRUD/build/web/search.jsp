<%-- 
    Document   : search
    Created on : 07-06-2024, 08:23:36
    Author     : PHUCHAU
--%>

<%--<%@page import="registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcom, ${sessionScope.USER.fullName}
        </font>
        <h1> Search Page </h1>
        <form action="DispatchServlet" method="POST">
            Name: <input type="text" name="txtSearch" value="${param.txtSearch}" /><br />
            <input type="submit" name="btAction" value="Search" class="access-form-btn login-btn"/>
            <input type="submit" value="Logout" name="btAction" />
        </form></br>

        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_REASULT}" />
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>password</th>
                            <th>lastname</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter" >
                        <form action="DispatchServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.getUsername()}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.getPassword()}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdim" value="ON" 
                                           <c:if test="${dto.role}">checked="checked"</c:if> />

                                    </td>
                                    <td>
                                    <c:url var="urlRewriting" value="DispatchServlet">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue"
                                           value="${searchValue}" /> 
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font color="red">
                No record is matched!!!
                </font>
            </h2>
        </c:if>
    </c:if>

    <%--<%
        //get all cookies
        Cookie[] cookies = request.getCookies();
        //check exiesd cookie
        if (cookies != null) {
            Cookie recentCookie = cookies[cookies.length - 1];
            String username = recentCookie.getName();
    %>
    <font color = "red">
    welcome, <%= username%>
    </font>
    <%
        }
    %>
    <div>
        <div>
            <h1> Welcome to DB Servlet </h1>
            <form action="DispatchServlet" method="POST">
                Name: <input type="text" name="txtSearch" value="<%= request.getParameter("txtSearch")%>" /><br />
                <input type="submit" name="btAction" value="Search" class="access-form-btn login-btn"/>
                <input type="submit" value="Logout" name="btAction" />
            </form></br>
            <%
                String searchValue = request.getParameter("txtSearch");
                if (searchValue != null) {//tranh bi truy cap truc tiep tu thanh address bar
                    List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_REASULT");
                    if (result != null) {// co ket qua
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>password</th>
                        <th>lastname</th>
                        <th>Role</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;// delete
                        for (RegistrationDTO dto : result) {//xu li tung dto
                            String urlRewriting = "DispatchServlet"
                                    + "?btAction=delete"
                                    + "&pk=" + dto.getUsername()
                                    + "&lastSearchValue=" + searchValue;
                    %>

                    <form action="DispatchServlet" method="POST">
                        <tr><!--Update -->
                            <td><%= ++count%></td>
                            <td>
                                <%= dto.getUsername()%>
                                <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                            </td>

                            <td>
                                
                                <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                            </td>
                            <td>
                                <%= dto.getFullName()%>
                            </td>
                            <td>
                                <input type="checkbox" name="chkAdim" value="ON" 
                                       <%
                                           if (dto.isRole()) {
                                       %>
                                       checked="checked"
                                       <%
                                           }
                                       %>
                                       />
                            </td>
                            <td>
                                <a href="<%= urlRewriting%>">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction" />
                                <input type="hidden" name="lastSearchValue"
                                       value="<%=searchValue%>" />
                            </td>
                        </tr>
                    </form>
                    <%
                        }
                    %>   
                    </tbody>
                </table>

                <%
                } else {//ko co ket qua
                %>  
                <h2>
                    No record matched!!!
                </h2>
                <%
                        }
                    }
                %>--%>
</div>
<a href=""></a>
</div>
</body>
</html>
