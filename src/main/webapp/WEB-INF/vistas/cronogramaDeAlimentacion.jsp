<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${not empty mensaje}">
	<div class="alert ${mensaje.gravedad}" role="alert">
		<strong>${mensaje.titulo}</strong> ${mensaje.detalle}
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
</c:if>
<table class="table table-bordered">
	<tr>
		<th>Estado</th>
		<th>Alimento</th>
		<th>Cantidad</th>
		<th>Fecha</th>
		<th>Horario</th>
		<th>Acciones</th>
	</tr>
	<c:forEach items="${cronograma}" var="item" varStatus="status">
		<tr>
			<td>
				<c:choose>
					<c:when test="${item.estado eq 'COMPLETO'}">
						<span class="badge badge-pill badge-success">COMPLETO</span>
					</c:when>
					<c:otherwise>
						<c:if test="${item.estado eq 'PENDIENTE'}">
							<span class="badge badge-pill badge-warning">PENDIENTE</span>
						</c:if>
						<c:if test="${item.estado eq 'VENCIDO'}">
							<span class="badge badge-pill badge-danger">VENCIDO</span>
						</c:if>
						<a id="botonModalEliminarCronograma">
							<i class="fas fa-thumbs-up mx-2 text-success" onclick="completarCronograma(${item.id}, ${item.planAlimentario.id});"></i>
						</a>
					</c:otherwise>
				</c:choose>
			</td>
			<td>${item.alimento.nombre}</td>
			<td>${item.cantidad}</td>
			<td><spring:eval expression="item.fecha" /></td>
			<td>${item.horario}</td>
			<td>
				<a id="botonModalEliminarCronograma" href="#modalEliminarCronograma" 
				   data-cronograma="${item.id}" data-plan="${item.planAlimentario.id}"
					role="button" data-toggle="modal" class="open-Modal">
					<i class="fas fa-trash mx-2 text-danger"></i>
				</a>
				<c:if test="${item.estado eq 'PENDIENTE'}">
					<a class="linkModalDinamico" id="botonEditarCronograma" data-cronograma="${item.id}">
						<i class="fas fa-edit mx-2 text-info"></i>
					</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>