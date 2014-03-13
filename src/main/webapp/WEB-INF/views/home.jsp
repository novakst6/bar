<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" contentType="text/html" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<a href="/bar/addUserRole.htm">Přidej uživatelskou roli</a>
<a href="/bar/addUser.htm">Přidej uživatele</a>
<h2>Dostupní uživatelé:</h2>
<table>
	<tr><th>ID</th><th>USERNAME</th><th>PASSWORD</th><th>ROLES</th></tr>
	<c:forEach items="${users}" var="u">
		<tr>
			<td>${u.id}</td>
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
<h2>Kategorie:</h2>
<table>
	<tr><th>ID</th><th>Category name</th></tr>
	<c:forEach items="${cats}" var="cat">
		<tr>
			<td>${cat.id}</td>
			<td>${cat.name}</td>
		</tr>
	</c:forEach>
</table>
<P>  The time on the server is ${time}. </P>
<p>Security context: ${user} ${role}</p>
</body>
</html>
