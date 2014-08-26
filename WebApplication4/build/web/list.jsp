<%-- 
    Document   : list
    Created on : Mar 24, 2014, 1:02:14 PM
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
        ${nevek}
        <c:forEach items="${nevek}" var="itm">
        ${itm}
        </c:forEach>
     
         <a href="index.jsp">INDEX</a><br>
    </body>
</html>
