<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : Sep 23, 2013, 3:02:40 PM
    Author     : gabor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index Page</title>
    </head>
    <body>
        <form name="select_name" action="process.jsp">
        <select name="data" />
        <jsp:useBean id="lista" scope="page" class="packages.QBean" />
                
        <c:forEach var="line" items="${lista.names}">
        <option><c:out value="${line}"/></option>
        </c:forEach>
        </select>
            <input type="submit" value="OK" />    
        </form>
    </body>
</html>
