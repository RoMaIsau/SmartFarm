<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../../parts/meta.jsp"%>

</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<%@ include file="../../parts/sidebar.jsp"%>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->

				<%@ include file="../../parts/topbar.jsp"%>

				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<h1 class="h3 mb-2 text-gray-800">Plan de alimentario</h1>
					<p class="mb-4">Genere el cronograma de alimentación para el animal seleccionado</p>
					<div class="container-fluid">
						<div class="h4 mb-2 text-gray-800">Datos del animal</div>
						<div class="table-responsive">
								<table class="table table-bordered">
									<tr>
										<th>Id</th>
										<th>Tipo</th>
										<th>Raza</th>
										<th>Género</th>
										<th>Peso</th>
									</tr>
									<tr>
										<td>${animal.id}</td>
										<td>${animal.tipo.nombre}</td>
										<td>${animal.raza.nombre}</td>
										<td>${animal.genero.nombre}</td>
										<td>${animal.peso}</td>
								</table>
						</div>
					</div>
					<div class="container-fluid mt-3">
						<div class="h4 mb-2 text-gray-800">Cronograma</div>
						<form:form action="crearPlanAlimentario" method="POST" modelAttribute="formulario">
							<div class="table-responsive">
								<table class="table table-bordered">
									<tr>
										<th>Número</th>
										<th>Alimento</th>
										<th>Cantidad</th>
										<th>Fecha</th>
										<th>Horario</th>
									</tr>
									<c:forEach items="${formulario.cronogramaDeAlimentacion}" var="cronograma" varStatus="status">
										<tr>
											<td align="center">${status.count}</td>
											<td>
												<select name="cronogramaDeAlimentacion[${status.index}].alimento.id" class="form-control">
													<c:forEach items="${alimentos}" var="alimento">
														<option value="${alimento.id}">${alimento.nombre}</option>
													</c:forEach>
												</select>
											</td>
											<td><input name="cronogramaDeAlimentacion[${status.index}].cantidad" value="${cronograma.cantidad}" class="form-control"/></td>
											<td><input name="cronogramaDeAlimentacion[${status.index}].fecha" value="${cronograma.fecha}" class="form-control"/></td>
											<td><input name="cronogramaDeAlimentacion[${status.index}].horario" value="${cronograma.horario}" class="form-control"/></td>
										</tr>
									</c:forEach>
								</table>
								<input type="submit" value="Crear" class="btn btn-primary btn-user btn-block text-white"/>
							</div>	
						</form:form>
					</div>
				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Smart Farm 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp"%>	
</body>
</html>