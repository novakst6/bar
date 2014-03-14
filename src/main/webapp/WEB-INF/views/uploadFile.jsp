<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Přidej soubor</h1>
	<f:form action="upload.htm" method="POST" commandName="formModel" enctype="multipart/form-data">
		<f:input path="file" type="file" />
		<f:errors path="*" />
		<input type="submit" value="Nahrát"/>
	</f:form>
	<h1>Nahrané soubory</h1>
	<table>
	<c:forEach items="${filesList}" var="f" >
		<tr>
		<td>${f.id}</td>
		<td>${f.name}</td>
		<td>${f.contentType}</td>
		<td><a href="download/${f.id}">DOWNLOAD</a></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>