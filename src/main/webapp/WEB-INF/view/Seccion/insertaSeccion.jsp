<%@include file="/commom/header.jsp" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>	
	
	<form:form action="insertSeccion" method="post" modelAttribute="seccion">
	<table>	
	<tr>
	<td><form:label path="nomseccion"><spring:message code="label.seccion.nombre"/></form:label></td>
	<td><form:input path="nomseccion"/></td>
	<td><form:errors path="nomseccion"/></td>
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