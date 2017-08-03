<%@include file="/commom/header.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form  method="post"
		modelAttribute="usuario">


		<table>
		<form:hidden path="idusuario" />

			<tr>
				<td><form:label path="nombre">
						<spring:message code="label.nombre" />
					</form:label></td>
				<td><form:input path="nombre" /></td>
				<td><form:errors path="nombre" /></td>
			</tr>

			<tr>
				<td><form:label path="apellidos">
						<spring:message code="label.apellidos"></spring:message>
					</form:label></td>
				<td><form:input path="apellidos" /></td>
				<td><form:errors path="apellidos" /></td>
			</tr>

			<tr>
				<td><form:label path="email">
						<spring:message code="label.email" />
					</form:label></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" /></td>
			</tr>

			<tr>
				<td><form:label path="edad">
						<spring:message code="label.edad" />

					</form:label></td>
				<td><form:input path="edad" /></td>
				<td><form:errors path="edad" /></td>
			</tr>


			<tr>
				<td><form:label path="tlf">
						<spring:message code="label.tlf" />

					</form:label></td>
				<td><form:input path="tlf" /></td>
				<td><form:errors path="tlf" /></td>
			</tr>


			<tr>
				<td><form:label path="fotoUrl">
						<spring:message code="label.fotoUrl" />

					</form:label></td>
				<td><form:input path="fotoUrl" /></td>
				<td><form:errors path="fotoUrl" /></td>
			</tr>

			<tr>
				<td><form:label path="registrado">
						<spring:message code="label.registrado" />

					</form:label></td>
				<td><form:input path="registrado" /></td>
				<td><form:errors path="registrado" /></td>
			</tr>

			<tr>
				<td><form:label path="clave">
						<spring:message code="label.clave" />
					</form:label></td>
				<td><form:input path="clave" /></td>
				<td><form:errors path="clave" /></td>
			</tr>
			<tr>
				<td><form:label path="reg_Date">
						<spring:message code="label.reg_Date" />
					</form:label></td>
				<td><form:input path="reg_Date" /></td>
				<td><form:errors path="reg_Date" /></td>
			</tr>


			<tr>
			
			<c:choose>
			<c:when test="${edit}">
			
			<!-- <td colspan="3"><form:button name="actualizar"><c:out value="Actualizar"></c:out></form:button> <form:button name="cancelar"><c:out value="Cancelar"></c:out></form:button></td>-->
			<td colspan="3"><input type="submit" value="Update"> or <a href="<c:url value='/list' />"><c:out value="Cancelar"/></a></td>
			</c:when>
			
			<c:otherwise>
			
			<!-- <td colspan="3"><form:button name="insertar"><c:out value="Insertar"></c:out></form:button> <form:button name="cancelar"><c:out value="Cancelar"></c:out></form:button></td>-->
			<td colspan="3"><input type="submit" value="Register" /> or  <a href="<c:url value='/list'/>"><c:out value="Cancelar"/></a>
			</c:otherwise>
			
			</c:choose>
			
			
			</tr>

		</table>
	</form:form>
</body>
</html>