<%-- 
    Document   : confirm
    Created on : Oct 11, 2013, 2:07:34 PM
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
        <jsp:useBean id="writein" scope="session" class="packages.WriteBean" />
        
        <jsp:setProperty name="writein" property="name" />
        <jsp:setProperty name="writein" property="place" />
        <jsp:setProperty name="writein" property="age" />
        The following data was received from the input form: <br>
        <jsp:getProperty name="writein" property="name" /> <br>
        <jsp:getProperty name="writein" property="place" /> <br>
        <jsp:getProperty name="writein" property="age" /> <br>

        <jsp:setProperty name="writein" property="entry" value="${writein.name}" />
    </body>
</html>
