<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<div class="container" style="max-width: 720px !important;">

						<div class="card o-hidden border-0 shadow-lg my-5">
							<div class="card-body p-0">
								<!-- Nested Row within Card Body -->
								<div class="row">
									<div class="col-lg-12">
										<div class="p-5">
											<div class="text-center">
												<h1 class="h4 text-gray-900 mb-4">Registrar usuario/a!</h1>
											</div>
											<%--Bloque que es visible si el elemento error no esta vacio --%>
											<c:if test="${not empty error}">
												<span class="text-danger" style="float: left;">${error}</span>
												<br>
											</c:if>
											<c:if test="${not empty mensaje}">
												<span class="text-success" style="float: left;">${mensaje}</span>
												<br>
											</c:if>
											<form:form method="POST" modelAttribute="usuario"
												action="validar-registro" class="user">
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<form:input path="nombre" type="text" id="nombre"
															name="nombre" required="true"
															class="form-control form-control-user"
															placeholder="Nombre" />
													</div>
													<div class="col-sm-6">
														<form:input path="apellido" type="text" id="apellido"
															name="apellido" required="true"
															class="form-control form-control-user"
															placeholder="Apellido" />
													</div>
												</div>
												<div class="form-group">
													<form:input path="email" type="email" id="email"
														name="email" required="true"
														class="form-control form-control-user" placeholder="Email" />
												</div>
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<form:input path="password" type="password" id="password"
															name="password" required="true"
															class="form-control form-control-user"
															placeholder="Contraseña" />
													</div>
													<div class="col-sm-6">
														<input type="password" id="password2" name="password2"
															required class="form-control form-control-user"
															placeholder="Repita contraseña">
													</div>
												</div>
												<div class="form-group">
													<form:select path="rol" id="tipoDeGasto" name="rol" required="true" class="form-control">
													   	<option value=""></option>
													   	<c:forEach items="${roles}" var="rols">
													   		<option value="${rols.nombre}">${rols.nombre}</option>
													   	</c:forEach>
													</form:select>
												</div>
												<button
													class="btn btn-primary btn-user btn-block text-white"
													type="submit">Registrar cuenta</button>
											</form:form>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>


				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2019</span>
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

	<!-- Modal eliminar usuario -->
	<div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Borrar usuario</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">¿Quiere borrar el usuario?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancelar</button>
					<a type="button" class="btn btn-danger text-white botonEliminar">Aceptar</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp"%>
</body>
</html>