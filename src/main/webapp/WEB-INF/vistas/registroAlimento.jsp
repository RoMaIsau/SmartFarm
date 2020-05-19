<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
												<h1 class="h4 text-gray-900 mb-4">Registro de alimento</h1>
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
											<form:form method="POST" modelAttribute="alimento"
												action="validar-registro-alimento" class="user">
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0">
														<form:input path="nombre" class="form-control"
															placeholder="Nombre" required="true" type="text" />
													</div>
													<div class="col-sm-6">
														<form:select id="alimento" path="tipo.id"
															class="form-control">
															<form:options items="${tiposAlimentos}"
																itemLabel="nombre" itemValue="id" class="dropdown-item" />
														</form:select>
													</div>
												</div>
												<div class="form-group row">
													<div class="col-sm-6 mb-3 mb-sm-0 input-group">
														<form:input path="cantidad" type="text" name="cantidad"
															required="true" class="form-control"
															placeholder="Cantidad" />
														<div class="input-group-append">
															<span class="input-group-text" id="basic-addon2">Kg.</span>
														</div>
													</div>
													<div class="col-sm-6 input-group">
														<form:input path="stockMinimo" type="text"
															name="stockMinimo" required="true" class="form-control"
															placeholder="Stock minimo" />
														<div class="input-group-append">
															<span class="input-group-text" id="basic-addon2">Kg.</span>
														</div>
													</div>
												</div>
												<button class="btn btn-primary btn-block text-white"
													type="submit">Registrar alimento</button>
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