<%@include file="/commom/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>listado de seccion</title>

</head>
<body>

	<div class="generic-container">

		<div class="panel panel-default">

			<div class="panel-heading">
				<span class="lead">List of Section</span>
			</div>
			<c:choose>
				<c:when test="${ ! empty listSection}">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>CODSECCION</th>
								<th>NOMBRE</th>
								<th width="100"></th>
								<th width="100"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listSection}" var="item">
								<tr>
									<td>${item.idseccion}</td>
									<td>${item.nomseccion}</td>
									<td><a href='<c:url value="/edit-section-${item.idseccion}"/>' class="btn btn-success customer-with">edit</a></td>
									<td><a href='<c:url value="/delete-section-${item.idseccion}"/>' class="btn btn-danger custom-width">delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>

					<c:out value="No existen registros"></c:out>

				</c:otherwise>

			</c:choose>
			
			<a href='<c:url value="/addSeccion"/>'><c:out value="newSeccion"/></a>

		</div>

	</div>

</body>
</html>