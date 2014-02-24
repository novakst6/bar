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
	<h1>Přidej Roli</h1>
	<f:form action="addUserRole.htm" method="POST" commandName="formModel">
		<f:input path="name"/>
		<input type="submit" value="Odeslat"/>
	</f:form>
	<h1>Dostupné role</h1>
	<table>
	<c:forEach items="${roleList}" var="r" >
		<tr>
		<td>${r.name}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>