<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="modal fade" id="modalDetalleCorral" tabindex="-1" role="dialog" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">${nombre}</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<c:choose>
						<c:when test="${fn:length(animalesDelCorral) eq 0}">
							<div class="alert alert-warning" role="alert">
								<strong>Este corral no posee animales asignados</strong>
							</div>
						</c:when>
						<c:otherwise>
					<div class="table-resposive">
						<table class="table table-bodered">
							<tr>
								<th>Id</th>
								<th>Tipo</th>
								<th>Raza</th>
								<th>Género</th>
								<th>Peso</th>
							</tr>
							<c:forEach items="${animalesDelCorral}" var="animal">
								<tr>
									<td>${animal.id}</td>
									<td>${animal.tipo.nombre}</td>
									<td>${animal.raza.nombre}</td>
									<td>${animal.genero.nombre}</td>
									<td>${animal.peso}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="container-fluid mt-3">
					<c:choose>
						<c:when test="${fn:length(animalesSueltos) gt 0}">
							<h5>Asignar animales:</h5>
							<form:form id="formAsignacionDeCorral" cssClass="form-group" modelAttribute="asignacion">
							<form:hidden path="idCorral"/>
							<c:forEach items="${animalesSueltos}" var="animal" varStatus="i">
								<div class="form-check">
									<form:checkbox cssClass="form-check-input" path="animalesSeleccionados" value="${animal.id}" id="check${i.count}"/>
									<label class="form-check-label" for="check${i.count}">
	    								${animal.tipo.nombre} - ${animal.identificadorGps} 
	  								</label>
								</div>
							</c:forEach>
							</form:form>
						</c:when>
						<c:when test="${fn:length(animalesSueltos) eq 0}">
							<c:set var="disabled" value="disabled"></c:set>
							<div class="alert alert-warning" role="alert">
								<strong>No hay animales disponibles sin corral asignado</strong>
							</div>
						</c:when>	
					</c:choose>					
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
				<input ${disabled} id="botonConfifmarAsignacionCorral" class="btn btn-danger botonEliminar" type="button" value="Asignar">
			</div>
		</div>
	</div>
</div>