<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title" /></title>
<link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet">
</head>
<body>
	<fieldset>
		<center>
			<b> <spring:message code="label.title" /></b>
		</center>
	</fieldset>
	<br>
	<div align="right">
		<a href="<c:url value="/logout" />"> <spring:message code="label.logout" />
		</a>
	</div>
	<div>
		<a href="<c:url value="/home" />"> <spring:message code="label.home" /></a> &gt; <a href="<c:url value="/user" />"> <spring:message
				code="label.user"
			/></a> &gt;
		<spring:message code="label.createuser" />
	</div>
	<br>
	<c:set var="a" value="ROLE_ADMIN"></c:set>
	<c:if test="${arole==a}">
		<c:if test="${user.id==null}">
			<c:set var="text" value="Создать участника"></c:set>
		</c:if>
		<c:if test="${user.id!=null}">
			<c:set var="text" value="Сохранить изменения"></c:set>
		</c:if>
	</c:if>
	<c:if test="${arole!=a}">
		<c:set var="text" value="Сохранить изменения"></c:set>
	</c:if>

	<b> Введите необходимые значения и нажмите кнопку </b>

	<form:form class="form" method="post" action="createuser" commandName="user">
		<table>
			<tr>
				<td class = "submit"><input  type="submit" value="${text}"></td>

				<td><form:select path="role">
						<form:option value="ROLE_USER" label=" Участник" />
						<c:if test="${arole==a}">
							<form:option value="ROLE_ADMIN" label=" Администратор" />
						</c:if>
					</form:select></td>
				<c:if test="${arole!=a}">
					<c:set var="role" value="ROLE_USER"></c:set>
				</c:if>
			</tr>
			<tr>
				<td>Имя :</td>
				<td><form:input path="firstname" /></td>
				<td><font color="red"><form:errors path="firstname" /></font></td>
			</tr>
			<tr>
				<td>Фамилия :</td>
				<td><form:input path="lastname" /></td>
				<td><font color="red"><form:errors path="lastname" /></font></td>
			</tr>
			<tr>
				<td>Никнейм :</td>
				<td><form:input path="nickname" /></td>
				<td><font color="red"><form:errors path="nickname" /></font><font color="red"><c:out value="${failed}" /></font></td>
			</tr>
			<tr>
				<td>Пароль :</td>
				<td><form:password path="password" /></td>
				<td><font color="red"><form:errors path="password" /></font></td>
			</tr>
		</table>
	</form:form>
	<font color="green"><c:out value="${success}"></c:out></font>

</body>
</html>