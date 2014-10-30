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
			<b> <spring:message code="label.title" />
			</b>
		</center>
	</fieldset>

	<div align="right">
		<br> <a href="<c:url value="/logout" />"> <spring:message code="label.logout" /></a>
	</div>
	<div>
		<a href="<c:url value="/home" />"> <spring:message code="label.home" /></a> &gt;
		<spring:message code="label.sendamessage" />
	</div>
	<br>
	<form:form class="form" method="post" actoin="send" commandName="message">
		<table>
			<tr>
				<td><form:label path="receiver">
				Получатель :
			</form:label></td>
				<td><form:input path="receiver"/> <input type="submit" style="background: #617798; color: #FFFFFF" value=" Отправить " /> <font color="red"><form:errors
							path="receiver"
						/></font></td>
			</tr>
			<tr>
				<td><form:label path="theme">
				Тема :
			</form:label></td>
				<td><form:input path="theme" style="width: 700px;" /></td>
			</tr>
			<tr>
				<td valign="top"><form:label path="text">
				Сообщение :
			</form:label></td>
				<td><form:textarea path="text" /></td>
			</tr>
		</table>
	</form:form>
	<font color="green"><form:errors path="message" /></font>
</body>
</html>