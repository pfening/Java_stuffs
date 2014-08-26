<%-- 
    Document   : login-success
    Created on : Mar 20, 2014, 2:52:00 PM
    Author     : gabor
--%>

<%@page import="com.javapoint.LoginBean"%>  
  
<p>You are successfully logged in!</p>  
<%  
LoginBean bean=(LoginBean)request.getAttribute("bean");  
out.print("Welcome, "+bean.getName());  
%>  
