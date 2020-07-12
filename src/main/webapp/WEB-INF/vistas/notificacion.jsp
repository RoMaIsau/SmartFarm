<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="position: absolute; top: 0; right: 0; z-index: 1;">
	<c:forEach items="${notificaciones}" var="notificacion">
		<div class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="z-index: 2;" 
			data-autohide="false" data-animation="false">
			<div class="toast-header">
				<strong class="mr-auto">${notificacion.titulo}</strong> <small>${notificacion.fecha}</small>
			</div>
			<div class="toast-body">${notificacion.detalles}</div>
			<div class="row p-2">
				<div class="col-md">
					<button id="notificacion-${notificacion.id}" type="button" class="btn btn-danger float-right btn-notificacion-vista">Visto</button>
				</div>
			</div>
		</div>
	</c:forEach>
</div>