
<%@include file="/commom/header.jsp"%>

<html>

<head>
</head>

<body>

	<c:choose>
		<c:when test="${! empty listaUsuarios}">

			<table>
				<tr>
					<td><c:out value="NOMBRE" /></td>
					<td><c:out value="APELLIDOS" /></td>
					<td></td>
					<td></td>
				</tr>
				<c:forEach items="${listaUsuarios}" var="item">
					<tr>
						<td><c:out value="${ item.nombre }"></c:out></td>
						<td><c:out value="${ item.apellidos }"></c:out></td>
						<td><a
							href="<spring:url value="/edit-user-${item.idusuario}"/>"><c:out
									value="editar" /></a></td>
						<td><a href="<spring:url value="/delete-user-${item.idusuario}" />"><c:out
									value="eliminar" /></a></td>
					</tr>
				</c:forEach>
			</table>

		</c:when>
		<c:otherwise>
			<c:out value="No existen registros" />
		</c:otherwise>
	</c:choose>
<a href="<c:url value='/addUsuario' />"><c:out value="newUser"/></a>
</body>

</html>