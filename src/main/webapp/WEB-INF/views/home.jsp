<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	WildFly + Spring + Hibernate!  
</h1>
<a href="/bar/addUserRole.htm">Test link - seznamy</a>
<P>  The time on the server is ${serverTime}. </P>
<p>Security context: ${user}</p>
</body>
</html>
