<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="dynamicinvoke.*" %>
<%
	InputStream is = new FileInputStream("c:/TestClass.class");
	byte[] b = new byte[is.available()];
	is.read(b);
	is.close();

	out.println("<textarea style='width:1000;height=800'>");
	out.println(JavaClassExecuter.execute(b));
	out.println("</textarea>"); 
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>