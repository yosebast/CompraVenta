<%@include file="/commom/header.jsp" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>

		
	
	<form:form action="updateProducto" method="post" enctype="multipart/form-data" modelAttribute="producto">
	
<table>
	<form:hidden path="idproducto" />
	<tr>
	<td><form:label path="seccion"><spring:message code="label.prod.seccion"/></form:label></td>
	<td>
	<form:select path="seccion.idseccion">
	<form:option value="0">-----seleccione la sección-----</form:option>
	<form:options items="${listaSeccion}" itemLabel="nomseccion"  itemValue="idseccion"  />	
	</form:select>
	</td>
	<td><form:errors path="seccion"/></td>
	</tr>
	
	<tr>
	<td><form:label path="categoria"><spring:message code="label.prod.categoria"/></form:label></td>
	<td>
	<form:select path="categoria.idcategoria">
	<form:option value="0">-----seleccione la categoría-----</form:option>
	<form:options items="${listaCategorias}" itemLabel="nomcategoria"  itemValue="idcategoria"  />	
	</form:select>
	</td>
	<td><form:errors path="categoria"/></td>
	</tr>
	
	<tr>
	<td><form:label path="subcategoria"><spring:message code="label.prod.subcategoria" /></form:label></td>
	<td><form:select path="subcategoria.idsubcategoria">
	<form:option value="0">-----seleccione la subcategoría-----</form:option>
	<form:options items="${listaSubcategoria}" itemLabel="nomsubcategoria"  itemValue="idsubcategoria" />
	</form:select></td>
	<td><form:errors path="subcategoria"/></td>
	</tr>
	
	<tr>
	<td><form:label path="titulo"><spring:message code="label.prod.titulo"/></form:label></td>
	<td><form:input path="titulo"/></td>
	<td><form:errors path="titulo"/></td>
	</tr>
		
	<tr>
	<td><form:label path="precio"><spring:message code="label.prod.precio"/></form:label></td>
	<td><form:input path="precio"/></td>
	<td><form:errors path="precio"/></td>
	</tr>
	
	<tr>
	<td><form:label path="fechaPublicacion"><spring:message code="label.prod.fechaPublicacion"/></form:label></td>
	<td><form:input path="fechaPublicacion"/></td>
	<td><form:errors path="fechaPublicacion"/></td>
	</tr>
	
	<tr>
	<td><form:label path="estado"><spring:message code="label.prod.estado"/></form:label></td>
	<td><form:input path="estado"/></td>
	<td><form:errors path="estado"/></td>
	</tr>
	
	<tr>
	<td><form:label path="visitas"><spring:message code="label.prod.visitas"/></form:label></td>
	<td><form:input path="visitas"/></td>
	
	</tr>
	
	<tr>
	<td><form:label path="nomImagen"><spring:message code="label.prod.nomImagen"/></form:label></td>
	<td><form:input path="nomImagen"/></td>
	<td><form:errors path="nomImagen"/></td>
	</tr>	
	
	<tr>
	<td><form:label path="urlFoto1"><spring:message code="label.prod.urlFoto1"/></form:label></td>
	 <td><input type="file" name="file" id="file" ></input></td>
	 <td><form:errors path="urlFoto1"/></td>
	</tr>	
	
	<tr>
	<td colspan="3">
	<form:button name="actualizar">Actualizar</form:button>
	<form:button name="cancelar">cancelar</form:button>
	</td>
	</tr>	
	
	</table>
	
	</form:form>
		
	</body>
</html>
