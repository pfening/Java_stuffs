<%-- 
    Document   : process
    Created on : Sep 25, 2013, 8:40:22 AM
    Author     : gabor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Process Page</title>
    </head>
    <body>
        <h1>
        <jsp:useBean id="test" scope="page" class="packages.QBean" />
        <jsp:setProperty name="test" property="data" />
        <jsp:getProperty name="test" property="name" />
        <jsp:getProperty name="test" property="place" />
        <jsp:getProperty name="test" property="age" /> </h1>     
        <a href="index.jsp">INDEX</a><br>
        <a href="write.jsp">WRITE</a>
    </body>
</html>
