<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<table class="table table-bordered">
	<tr>
		<th>Alimento</th>
		<th>Cantidad</th>
		<th>Fecha</th>
		<th>Horario</th>
		<th>Acciones</th>
	</tr>
	<c:forEach items="${cronograma}" var="item" varStatus="status">
		<tr>
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
				<a href="#" id="botonEditarCronograma" data-cronograma="${item.id}">
					<i class="fas fa-edit mx-2 text-info"></i>
				</a>
			</td>
		</tr>
	</c:forEach>
</table>