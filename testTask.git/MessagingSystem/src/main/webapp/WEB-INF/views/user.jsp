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
<link href="<c:url value="/resources/css/table.css"/>" rel = "stylesheet" >
</head>
<fieldset>
	<center>
		<b><spring:message code="label.title" /></b>
	</center>
</fieldset>
<br>
<div align="right">
	<a href="<c:url value="/logout" />"> <spring:message
			code="label.logout" />
	</a>
</div>
<div>
	<a href="<c:url value="/home" />"> <spring:message
			code="label.home" /></a> &gt;
	<spring:message code="label.user" />
</div>

<c:set var="a" value="ROLE_ADMIN"></c:set>
<c:if test="${role==a}">
	<c:set var="text" value="Создать участника" />
	<c:set var="ref" value="/newuser" />
</c:if>
<c:if test="${role!=a}">
	<c:set var="text" value="Редактировать профиль" />
	<c:set var="ref" value="/createuser" />
</c:if>

<div align="right">
	<a href="<c:url value="${ref}"/>"><input type="button"
		class="button" value="${text}"></a>
</div>
<b>Список участников в системе</b>
<br>
<c:if test="${!empty userList}">
<br>
	<table class="table" cellspacing='0'>
		<tr>
			<th>Фамилия</th>
			<th>Имя</th>
			<th>Никнейм</th>
			<c:if test="${role==a}">
				<th>Пароль</th>
				<th>Действие</th>
			</c:if>
		</tr>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.firstname}</td>
				<td>${user.lastname}</td>
				<td>${user.nickname}</td>
				<c:if test="${role==a}">
					<td>${user.password}</td>
					<td><a href="<c:url value="/edituser/${user.id}" />">Модифицировать
					</a> <a href="<c:url value="/deleteuser/${user.id}" />">Удалить </a> <a
						href="<c:url value="/message/RECEIVED/${user.nickname}" />">Полученные
					</a> <a href="<c:url value="/message/SENT/${user.nickname}" />">Отправленные
					</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>