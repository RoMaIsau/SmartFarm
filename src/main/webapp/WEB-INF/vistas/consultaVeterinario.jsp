<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<%@ include file="../../parts/meta.jsp"%>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap/js/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap/js/bootstrap-theme.min.css" rel="stylesheet">
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
					<h1 class="h3 mb-3 text-gray-800">Sección para diagnosticar al animal</h1>
					<div class="container" style="max-width: 720px !important;">
						<div class="card o-hidden border-0 shadow-lg my-5">
							<div class="card-body p-0">
								<!-- Nested Row within Card Body -->
								<div class="row">
									<div class="col-lg-12">
										<div class="p-5">
											<div class="text-center">
												<h1 class="h4 text-gray-900 mb-4">Ingrese los síntomas observados</h1>
												<hr class="colorgraph"><br>
											</div>
											
											<!-- div id="loginbox" style="margin-top:50px;" -->
											<form:form action="diagnosticarPost" method="POST" modelAttribute="sintomas">
					                			<form:input path="idAnimal" id="idA" type="hidden" class="form-control" value="${idAnimal}"></form:input>
												<div class="row">
													<div class="col-sm-12 col-md-6 mb-3 mb-sm-0">
														<div class="form-group">
															<div class="form-check form-check-inline">
															  <form:checkbox path="Ulceras" class="form-check-input" id="ulceras" name="ulceras" value="true"/>
															  <label class="form-check-label" for="ulceras">Úlceras en ubres y boca</label>
															</div>
														</div>
													</div>
													<div class="col-sm-12 col-md-6 mb-3 mb-sm-0">
														<div class="form-group">
															<div class="form-check form-check-inline">
															  <form:checkbox path="anorexia" class="form-check-input" id="anorexia" name="anorexia" value="true"/>
															  <label class="form-check-label" for="anorexia">Falta de apetito</label>
															</div>
														</div>
													</div>
												</div>
												
												<div class="row">
													<div class="col-sm-12 col-md-6 mb-3 mb-sm-0">
														<div class="form-group">
															<div class="form-check form-check-inline">
															  <form:checkbox path="debilidad" class="form-check-input" id="debilidad" name="debilidad" value="true"/>
															  <label class="form-check-label" for="debilidad">Debilidad</label>
															</div>
														</div>
													</div>
													<div class="col-sm-12 col-md-6 mb-3 mb-sm-0">
														<div class="form-group">
															<div class="form-check form-check-inline">
															  <form:checkbox path="bajaProduccionLeche" class="form-check-input" id="bajaProduccion" name="bajaProduccion" value="true"/>
															  <label class="form-check-label" for="bajaProduccionLeche">Baja producción de leche</label>
															</div>
														</div>
													</div>
												</div>
												
												<div class="row">
													<div class="col-sm-12 col-md-6 mb-3 mb-sm-0">
														<div class="form-group">
															<div class="form-check form-check-inline">
															  <form:checkbox path="diarrea" class="form-check-input" id="diarrea" name="diarrea" value="true"/>
															  <label class="form-check-label" for="diarrea">Diarrea</label>
															</div>
														</div>
													</div>
													<div class="col-sm-12 col-md-6 mb-3 mb-sm-0">
														<div class="form-group">
															<div class="form-check form-check-inline">
															  <form:checkbox path="conjuntivitis" class="form-check-input" id="conjuntivitis" name="conjuntivitis" value="true"/>
															  <label class="form-check-label" for="conjuntivitis">Conjuntivitis</label>
															</div>
														</div>
													</div>
												</div>
												
												<div class="row">
													<div class="col-sm-12 col-md-6 mb-3 mb-sm-0">
														<div class="form-group">
															<div class="form-check form-check-inline">
															  <form:checkbox path="tos" class="form-check-input" id="tos" name="tos" value="true"/>
															  <label class="form-check-label" for="tos">Tos</label>
															</div>
														</div>
													</div>
													<div class="col-sm-12 col-md-6 mb-3 mb-sm-0">
														<div class="form-group">
															<div class="form-check form-check-inline">
															  <form:checkbox path="salivacionEspumosa" class="form-check-input" id="saliva" name="saliva" value="true"/>
															  <label class="form-check-label" for="saliva">Salivación espumosa</label>
															</div>
														</div>
													</div>
												</div>
												
												<div class="row">
													<div class="col-sm-12 col-md-6 mb-3 mb-sm-0">
														<div class="form-group">
															<div class="form-check form-check-inline">
															  <form:checkbox path="secrecionNasal" class="form-check-input" id="secrecion" name="secrecion" value="true"/>
															  <label class="form-check-label" for="secrecion">Secreción nasal</label>
															</div>
														</div>
													</div>
												</div>
												
												<c:if test="${empty fecha}"><br>
													<h5>¿A partir de que momento desea chequear los signos vitales del animal?</h5>
							       					<form:select path="tiempo" class="form-control">
												       	<form:option value="Una semana" type="text" class="form-control"/>UNA SEMANA	
												    	<form:option value="Un mes" type="text" class="form-control"/>UN MES
												       	<form:option value="Un año" type="text" class="form-control"/>UN AÑO
						              				</form:select>  
						               			</c:if>
						                		<c:if test="${not empty fecha}">
						                			<form:input type="hidden" value="Una semana" path="tiempo"></form:input>
						                 		</c:if><br>
												<button class="btn btn-success btn-block" Type="Submit">Consultar</button>
											</form:form>
											<!-- /div -->
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
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
	
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>
	
	<!-- Logout Modal-->
	<%@ include file="../../parts/modalCerrarSesion.jsp" %>
	
	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp" %>
</body>
</html>
