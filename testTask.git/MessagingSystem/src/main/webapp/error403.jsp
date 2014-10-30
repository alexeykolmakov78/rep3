
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title" /></title>
</head>
<body>
	<div>
		<br> Отказано в доступе.<br> Возможно вы не обладаете
		необходимыми правами<br>или ввели неверные данные. <a
			href="<c:url value="/login.jsp"/>"><input type="button"
			value=" OK "></a>
	</div>
</body>
</html>