<%@include file="/commom/header.jsp" %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>

		
	
	<form:form  method="post" modelAttribute="usuario">
	
		<table>
		
		    <tr>
				<td><form:label path="nombre"><spring:message code="label.nombre"/>
						
					</form:label></td>
				<td><form:input path="nombre" /></td>
				<td><form:errors path="nombre" /></td>
			</tr>
		
			<tr>
				<td><form:label path="apellidos"><spring:message code="label.apellidos"/>
						
					</form:label></td>
				<td><form:input path="apellidos" /></td>
				<td><form:errors path="apellidos" /></td>
			</tr>

			<tr>
				<td><form:label path="email"><spring:message code="label.email"/>
						
					</form:label></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" /></td>
			</tr>

			<tr>
				<td><form:label path="edad"><spring:message code="label.edad"/>
						
					</form:label></td>
				<td><form:input path="edad" /></td>
				<td><form:errors path="edad" /></td>
			</tr>
			
			
			<tr>
				<td><form:label path="tlf"><spring:message code="label.tlf" />
						
					</form:label></td>
				<td><form:input path="tlf" /></td>
				<td><form:errors path="tlf" /></td>
			</tr>
			

			<tr>
				<td><form:label path="fotoUrl"><spring:message code="label.fotoUrl"/>
						
					</form:label></td>
				<td><form:input path="fotoUrl" /></td>
				<td><form:errors path="fotoUrl" /></td>
			</tr>

			<tr>
				<td><form:label path="registrado"><spring:message code="label.registrado"/>
						
					</form:label></td>
				<td><form:input path="registrado" /></td>
				<td><form:errors path="registrado" /></td>
			</tr>

			<tr>
				<td><form:label path="clave"><spring:message code="label.clave" />						
					</form:label></td>
				<td><form:input path="clave" /></td>
				<td><form:errors path="clave" /></td>
			</tr>
			<tr>
				<td><form:label path="reg_Date"><spring:message code="label.reg_Date" />						
					</form:label></td>
				<td><form:input path="reg_Date" /></td>
				<td><form:errors path="reg_Date" /></td>
			</tr>
			
			<tr>
			
			<c:choose>
			
			<c:when test="${edit}">			
			<td colspan="3"><input type="submit" value="ruta"/> or <a href="<c:url value='/list' />">Cancel</a>
			</c:when>
			<c:otherwise>			
			<td colspan="3"><input type="submit" value="insertar">or <a href="<c:url value='/list' />">Cancel</a>
			</c:otherwise>		
			</c:choose>
			
			
				<!-- <td colspan="3"><form:button name="insertar">Insertar</form:button>
					<form:button name="cancelar">Cancelar</form:button></td>-->
			</tr>
			
			
			<form:button name="cargaUsuarios">cargaUsuarios</form:button>
			
			
		</table>
	
	
	</form:form>
	
		
		
		
		 <a href="cargaUsuarios">CargaUsuarios1</a>
		 <a href="cargaProductos">CaragaProductos</a>
		 
 		<input type="button" id="usuarios" name="usuarios" value="usuarios" onclick="location.href='<c:url value="cargaUsuarios"/>'"/>	 		
 		<input type="button" name="categoria" id="categoria" value="categoria" onclick="location.href='<c:url value="insertaCategoria"/>'"/> 		
 		<input  type="button" name="subcategoria" id="subcategoria" value="subcategoria" onclick="location.href='<c:url value="InsertaSubCategoria"/>'"/> 
 		<input type="button" id="productos" name="productos" value="productos"  onclick="location.href='<c:url value="InserProd"/>'"/> 
 		<input type="button" id="seccion" name="seccion" value="seccion"  onclick="location.href='<c:url value="cargaSeccion"/>'"/> 
		
		
	</body>
</html>
