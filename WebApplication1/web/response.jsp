<%-- 
    Document   : response
    Created on : Sep 13, 2013, 10:07:06 PM
    Author     : gabor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="mybean" scope="session" class="newpack.NameHandler" />
        <jsp:setProperty name="mybean" property="name" />
        <h1>Hello <jsp:getProperty name="mybean" property="name" />!</h1>
    </body>
</html>
