<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title" /></title>
<link href="<c:url value="/resources/css/table.css"/>" rel="stylesheet">
</head>
<fieldset>
	<center>
		 <b><spring:message code="label.title" /></b> 
	</center>
</fieldset>
<br>
<div align="right">
	<a href="<c:url value="/logout" />"> <spring:message code="label.logout" />
	</a>
</div>
<c:set var="s" value="SENT"></c:set>
<c:set var="r" value="RECEIVED"></c:set>
<c:choose>
	<c:when test="${messagetype==s}">
		<c:set var="type" value="Отправленные "></c:set>
		<c:set var="tabletype" value="Получатель "></c:set>
	</c:when>
	<c:when test="${messagetype==r}">
		<c:set var="type" value="Принятые "></c:set>
		<c:set var="tabletype" value="Отправитель "></c:set>
	</c:when>
</c:choose>
<div>
	<a href="<c:url value="/home" />"> <spring:message code="label.home" />
	</a> &gt; <a href="<c:url value="/user"/>"><spring:message code="label.user" /></a> &gt;
	<c:out value="${type}" />
</div>
<br>
<b>${type} сообщения участника <c:out value="${nickname}" /></b><br>
<br>
<c:if test="${!empty messageList}">
	<table class="table" cellspacing='0'>
		<tr>
			<th>${tabletype}</th>
			<th>Тема</th>
			<th>Сообщение</th>
			<th>Действие</th>
		</tr>
		<c:forEach items="${messageList}" var="message">
			<tr>
				<c:choose>
					<c:when test="${messagetype==r}">
						<td>${message.sender}</td>
					</c:when>
					<c:when test="${messagetype==s}">
						<td>${message.receiver}</td>
					</c:when>
				</c:choose>
				<td>${message.theme}</td>
				<td style="text-align: left;">${message.text}</td>
				<td><a href="<c:url value="/deletemessage/${messagetype}/${nickname}/${message.id}"/>">Удалить </a></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${empty messageList}">Сообщений нет.</c:if>

</body>
</html>