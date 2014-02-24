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
	<f:form action="addUser.htm" commandName="formModel" method="POST">
		<f:input path="username"/>
		<f:password path="password"/>
		<c:forEach items="${roles}" var="r">
		<f:label path="roles">
		<f:checkbox path="roles" value="${r.id}"/>${r.name}
		</f:label>
		</c:forEach>
		<input type="submit" value="Odeslat" />
	</f:form>
	
	<h1>Dostupní uživatelé</h1>
	<table>
	<c:forEach items="${userList}" var="u" >
		<tr>
		<td>${u.username}</td>
		<td>${u.password}</td>
		<td>
			<c:forEach items="${u.roles}" var="r">
				${r.name}<br />
			</c:forEach>
		</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>