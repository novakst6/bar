<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@ page pageEncoding="UTF-8" contentType="text/html" %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="cs" lang="cs">
<head>
	<title>IS: Administrace</title>
	<script src="<c:url value='/files/js/pace/pace.min.js' />"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/files/js/pace/pace.css' />" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/files/css/style_admin.css' />" />
	<link rel="shortcut icon" href="<c:url value='/files/css/fav1.ico' />" />
	<script src="<c:url value='/files/js/jquery-1.10.2.min.js' />"></script>
	<script src="<c:url value='/files/js/jqui/jquery-ui.min.js' />"></script>
	<script src="<c:url value='/files/js/app/appboot.js' />"></script>
	<link rel="stylesheet" href="<c:url value='/files/js/jqui/jquery-ui.min.css' />" />
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400' rel='stylesheet' type='text/css' />
</head>
<body>
	<div id="frameholder">
		<div id="centerframe">
			<div id="upmenu"></div>			
			<div id="secmenu"></div>			
			<div id="mainview"></div>			
			<div id="footer" style="">
				Spring version: ${springVersion}, Server time: ${time}
			</div>
		</div>
	</div>
</body>
</html>
