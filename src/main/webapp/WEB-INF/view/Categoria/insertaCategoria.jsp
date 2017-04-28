<%@include file="/commom/header.jsp" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>

		
	
	<form:form action="insertaCateg" method="post" modelAttribute="categoria">
	<table>	
	<tr>
	<td><form:label path="nomcategoria"><spring:message code="label.cate.nombre"/></form:label></td>
	<td><form:input path="nomcategoria"/></td>
	<td><form:errors path="nomcategoria"/></td></tr>
	<tr>
	<td><form:label path="descripcion"><spring:message code="label.cate.descripcion"/></form:label></td>
	<td><form:input path="descripcion"/></td>
	<td><form:errors path="descripcion"/></td>
	</tr>
	
	<tr>
	<td colspan="3">
	<form:button name="insertar">Insertar</form:button>
	<form:button name="cancelar">cancelar</form:button>
	</td>
	</tr>
	
	</table>
	
	</form:form>
	
	
	</body>
</html>