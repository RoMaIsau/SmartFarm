<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal fade" id="modalEditarCronograma" tabindex="-1" role="dialog" aria-modal="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Editar cronograma</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<form:form id="formEdicionDeCronograma" modelAttribute="cronogramaEditable">
						<form:input type="hidden" path="id"/>
						<form:input type="hidden" path="planAlimentario.id"/>
						<div class="row">
							<div class="col">
								<form:select path="alimento.id" class="form-control">
									<form:options items="${alimentos}" itemLabel="nombre" itemValue="id" />
								</form:select>
							</div>
							<div class="col">
								<form:input path="cantidad" type="text" class="form-control" placeholder="cantidad"/>
							</div>
							<div class="col">
								<form:input path="fecha" type="text" class="form-control" placeholder="Fecha"/>
							</div>
							<div class="col">
								<form:input path="horario" type="text" class="form-control" placeholder="Horario"/>
							</div>
						</div>
					</form:form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-dismiss="modal">Cancelar</button>
				<input id="botonAceptarEdicionCronograma" class="btn btn-danger botonEliminar" type="button" value="Confirmar">	
			</div>
		</div>
	</div>
</div>
