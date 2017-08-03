<%@include file="/commom/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Section Registration Form</title>
</head>
<body>
	<div class="generic-container">
		<div class="well lead">Section Registration Form</div>
		<form:form method="post" modelAttribute="seccion"
			class="form-horizontal">
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="idSeccion">Cod
						Seccion</label>
					<div class="col-md-7">
						<form:input path="idseccion" type="text" id="idseccion"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="idseccion" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="nomseccion">
						Nombre</label>
					<div class="col-md-7">
						<form:input path="nomseccion" type="text" id="nomseccion"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="nomseccion" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/listSection' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/listSection'/>">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>