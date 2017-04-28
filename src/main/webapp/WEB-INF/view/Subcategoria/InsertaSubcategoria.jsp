<%@include file="/commom/header.jsp" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>

		
	
	<form:form action="insertaSubCatego" method="post" modelAttribute="subcategoria">
	<table>
	<tr>
	<td><form:label path="categoria"><spring:message code="label.cate.idcate"/></form:label></td>
	<td><form:select path="categoria.idcategoria">
	<form:options items="${listaCategorias}" itemLabel="nomcategoria" itemValue="idcategoria"/>
	</form:select></td>
	<td><form:errors path="categoria"/></td>
	</tr>
	<tr>
	<td><form:label path="nomsubcategoria"><spring:message code="label.sub.nombre"/></form:label></td>
	<td><form:input path="nomsubcategoria"/></td>
	<td><form:errors path="nomsubcategoria"/></td>
	
	</tr>
	
	<tr><td colspan="3">
	<form:button name="insertar">Insertar</form:button>
	<form:button name="cancelar">Cancelar</form:button>
	</td></tr>
	
	
	</table>
	
	
	
	</form:form>
	
		
		
		
		
		
	</body>
</html>