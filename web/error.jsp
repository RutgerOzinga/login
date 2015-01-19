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
        <h1>Whoops!</h1>
        <h3>Someone's totally gonna pay for this</h3>
        
        <br />
        Something went wrong:
        ${requestScope.error}
    </body>
</html>
