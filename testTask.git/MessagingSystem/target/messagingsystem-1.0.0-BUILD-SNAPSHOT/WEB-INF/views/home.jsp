<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<link href="<c:url value="/resources/css/table.css"/>" rel="stylesheet">
<title><spring:message code="label.title" /></title>
</head>
<body>
	<fieldset>
		<center>
			<b><spring:message code="label.title" /> </b>
		</center>
	</fieldset>
	<br>
	<div align="right">
		<a href="<c:url value="/logout" />"> <spring:message code="label.logout" />
		</a>
	</div>
	<a href="<c:url value="/user" />"> <spring:message code="label.user" />
	</a>
	<br>
	<br>
	<a href="<c:url value="/send" />"> <spring:message code="label.sendamessage" />
	</a>
	<br>
	<br>
	<a href="<c:url value="/message/RECEIVED" />"> <spring:message code="label.received" />
	</a>
	<br>
	<br>
	<a href="<c:url value="/message/SENT" />"> <spring:message code="label.sent" />
	</a>
</body>
</html>
