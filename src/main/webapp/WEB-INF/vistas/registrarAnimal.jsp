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
												<h1 class="h4 text-gray-900 mb-4">Registrar Animal</h1>
											</div>
											<form:form action="registrar" method="post" modelAttribute="animal">
												<div class="row">
													<div class="col-sm-6">
														<div class="form-group">
															<label for="tipo">Tipo Animal</label>
															<form:select id="tipo" path="tipo.id" class="form-control">
																<form:options items="${tiposDeAnimales}" itemLabel="nombre" itemValue="id"/>
															</form:select>
														</div>
													</div>
													<div class="col-sm-6">
														<div class="form-group">
															<label for="raza">Raza</label>
															<form:select id="raza" path="raza.id" class="form-control">
																<form:options items="${razas}" itemLabel="nombre" itemValue="id"/>
															</form:select>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-6">
														<div class="form-group">
															<label for="genero">Género</label>
															<form:select id="genero" path="genero.id" class="form-control">
																<form:options items="${generos}" itemLabel="nombre" itemValue="id"/>
															</form:select>
														</div>
													</div>
													<div class="col-sm-6">
														<div class="form-group">
															<label for="peso">Peso</label>
															<form:input path="peso" class="form-control"/>
														</div>
													</div>
												</div>
												<form:button class="btn btn-primary btn-user btn-block text-white">Registrar</form:button>
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


	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp"%>

	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp"%>
	<script src="<c:url value="/js/registrarAnimal.js"/>"></script>
</body>
</html>