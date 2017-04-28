

<%@include file="/commom/header.jsp" %>


<html>
<head>


</head>
<body>

<c:choose>

<c:when test="${! empty listaUsuarios}">

<table>

<tr><td><c:out value="id"/></td><td><c:out value="NOMBRE"/></td><td><c:out value="APELLIDOS"/></td><td></td></td></tr>

<c:forEach items="${listaUsuarios}"  var="item" >

<tr>
<td><a href="<spring:url value="ruta?idUsuario=${item.idusuario}"/>"><c:out value="${item.idusuario}"></c:out></a></td><td><c:out value="${ item.nombre }"></c:out></td>
<td><c:out value="${ item.apellidos }"></c:out></td><td><a href="<spring:url value="delete/${item.idusuario}" />">eliminar</a></td>
</tr>


</c:forEach>

</table>

</c:when>
<c:otherwise>

<c:out value="No hay datos"/>


</c:otherwise>


</c:choose>



</body>
</html>