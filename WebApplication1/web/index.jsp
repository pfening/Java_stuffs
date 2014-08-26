<%-- 
    Document   : index
    Created on : Jul 18, 2013, 3:16:53 PM
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
        <h1>Entry form</h1>
           <form name="Name Input Form" action="response.jsp">
           Name <select name="name">
                <option>Gabor</option>
                <option>Alex</option>
                <option>Aleksandra</option>
            </select>
           <input type="submit" value="Send" />
        </form>
        
        
    </body>
</html>
