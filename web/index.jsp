<%-- 
    Document   : index
    Created on : Dec 30, 2014, 6:24:36 AM
    Author     : Raozinga
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="includes/loginHtmlHeader.jsp">
            <jsp:param name="page_title" value="Home page" />	
        </jsp:include>    
        <script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
        <script src="js/gaWerken.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>Hello World!</h1>


<!--    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <%--<jsp:include page="includes/loginForm.jsp"/>--%>
        </c:when>
        <c:otherwise>
            <%--<jsp:include page="includes/workshop1.jsp"/>--%>
            <h1>Hallo ${sessionScope.user.username}</h1>
        </c:otherwise>
    </c:choose>-->
    <div id = "toChange" data-user = ${sessionScope.user.username}>
        <jsp:include page="includes/loginForm.jsp"/>
    </div>

</body>
</html>
