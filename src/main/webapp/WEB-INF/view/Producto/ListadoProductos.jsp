

<%@include file="/commom/header.jsp" %>


<html>
<head>


</head>
<body>

<c:choose>

<c:when test="${! empty listaProductos}">

<table>

<tr>
<td><c:out value="id"/></td>
<td><c:out value="SUBCATEGORIA"/></td>
<td><c:out value="CATEGORIA"/></td>
<td><c:out value="SECCION"/></td>
<td><c:out value="TITULO"/></td>
<td><c:out value="PRECIO"/></td>
<td><c:out value="FECHAPUBLICACION"/></td>
<td><c:out value="ESTADO"/></td>
<td><c:out value="VISITAS"/></td>
<td><c:out value="NOMIMAGEN"/></td>

</tr>

<c:forEach items="${listaProductos}"  var="item" >

<tr>
<td><a href="<spring:url value="rutaProducto?idproducto=${item.idproducto}"/>"><c:out value="${item.idproducto}"></c:out></a></td>
<td><c:out value="${item.subcategoria.nomsubcategoria}"></c:out></td>
<td><c:out value="${ item.categoria.nomcategoria }"></c:out></td>
<td><c:out value="${ item.seccion.nomseccion }"></c:out></td>
<td><c:out value="${ item.titulo }"></c:out></td>
<td><c:out value="${ item.precio }"></c:out></td>
<td><c:out value="${ item.fechaPublicacion }"></c:out></td>
<td><c:out value="${ item.estado }"></c:out></td>
<td><c:out value="${ item.visitas }"></c:out></td>
<td><c:out value="${ item.nomImagen }"></c:out></td>
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