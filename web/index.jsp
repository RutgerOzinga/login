<%-- 
    Document   : index
    Created on : Dec 30, 2014, 6:24:36 AM
    Author     : Raozinga
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="includes/loginHtmlHeader.jsp">
            <jsp:param name="page_title" value="Home page" />	
        </jsp:include>    
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <jsp:include page="includes/loginForm.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="includes/logout.jsp"/>
           
            </c:otherwise>
        </c:choose>
    </body>
</html>
