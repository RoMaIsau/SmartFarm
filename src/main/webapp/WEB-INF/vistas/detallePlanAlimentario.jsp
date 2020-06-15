<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="h4 mb-2 text-gray-800">Cronograma</div>
<form id="formPlanAlimentario">
	<input type="hidden" name="animal.id" value="${formulario.animal.id}">
	<input type="hidden" name="planAlimentario.id" value="${formulario.planAlimentario.id}">

	<div class="row">
		<div class="col">
			<select id="alimento" name="itemCronograma.alimento.id" class="form-control">
				<c:forEach items="${formulario.alimentos}" var="alimento">
					<option value="${alimento.id}">${alimento.nombre}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col">
			<input id="cantidad" type="text" name="itemCronograma.cantidad" class="form-control" placeholder="cantidad">
		</div>
		<div class="col">
			<input id="fecha" type="text" name="itemCronograma.fecha" class="form-control" placeholder="Fecha">
		</div>
		<div class="col">
			<input id="horario" type="text" name="itemCronograma.horario" class="form-control" placeholder="Horario">
		</div>
		<div class="col">
			<button id="botonAgregarCronograma" class="btn btn-primary"> <i class="fa fa-plus"></i> Agregar</button>
		</div>
	</div>
</form>
<div id="cronogramaDeAlimentacion" class="table-responsive mt-5">
	<%@ include file="cronogramaDeAlimentacion.jsp" %>
</div>
<!-- Modal eliminar cronograma -->
<div class="modal fade" id="modalEliminarCronograma" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Eliminar cronograma</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">¿Quiere borrar el cronograma?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-dismiss="modal">Cancelar</button>
				<form id="formEliminarCronograma" action="animales/eliminarCronograma" method="POST">
					<input id="idCronogramaParaEliminar" type="hidden" name="idCronograma">
					<input id="idPlanDeCronogramaEliminado" type="hidden" name="idPlan">
					<input id="botonEliminarCronograma" class="btn btn-danger botonEliminar" type="button" value="Aceptar">
				</form>
			</div>
		</div>
	</div>
</div>
<!-- Modal dinámico editar cronograma -->
<div id="contenedorModalEditarCronograma">

</div>