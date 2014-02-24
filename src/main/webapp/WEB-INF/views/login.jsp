<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


                <form class=".form-horizontal" action="<spring:url value="j_spring_security_check"/>" method="post">
                    <c:if test="${not empty param.error}">
                        <div class="alert alert-error">
                            <b>Ouha! </b>Chybné uživatelské jméno nebo heslo!

                        </div>
                    </c:if>   
                    <div class="control-group">
                        <label for="j_username">Uživatelské jméno:</label>
                        <input type="text" name="j_username" id="j_username" />                   
                    </div>
                    <div class="control-group">
                        <label for="j_password">Heslo:</label>                       
                        <input type="password" name="j_password" id="j_password" />
                    </div>
                    <div class="control-group">
                        <button type="submit" class="btn" id="btn_login">Odeslat</button>
                    </div>
                </form>
</body>
</html>