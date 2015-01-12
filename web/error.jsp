<%-- 
    Document   : error
    Created on : Jan 12, 2015, 1:41:16 PM
    Author     : raozinga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Error Page</h1>
        Something went wrong:<br />
        ${requestScope.error}
    </body>
</html>
