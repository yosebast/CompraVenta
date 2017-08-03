<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome to private management</title>
	</head> 
	<body>
		<c:url value="/list" var="messageUrl" />
		<a href="${messageUrl}">Gestion de usuarios</a>
		
		<!--<c:url value="/showProducts" var="messageUrl" />
		<a href="${messageUrl}">Gestion de productos</a>
		
		<c:url value="/showProducts" var="messageUrl" />
		<a href="${messageUrl}">Gestion de categorias</a>
		
		<c:url value="/showProducts" var="messageUrl" />
		<a href="${messageUrl}">Gestion de subcategorias</a>-->
		
		<c:url value="/listSection" var="messageUrl" />
		<a href="${messageUrl}">Gestion de secciones</a>
	</body>
</html>
