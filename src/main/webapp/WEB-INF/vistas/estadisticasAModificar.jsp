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
				
					<h1 class="h3 mb-2 text-gray-800">Estad�stica N� ${gastos.id}</h1>
					 
					<c:if test="${not empty gastosAntiguos}">
						<div class="card shadow mb-4">
							<div class="card-header py-3 mx-0 row justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">Datos antiguos</h6>
								</a>
							</div>
							
							<div>
							</div>
							
							<div class="card-body">
								<div class="table-responsive">
									<table class="table text-center" id="" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>Tipo de gasto</th>
												<th>Monto</th>
											</tr>
										</thead>
										<tbody>
												<tr>
													<td><c:out value="${gastosAntiguos.tipoDeGasto}" /></td>
													<td>$ <c:out value="${gastosAntiguos.monto}" /></td>
												</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:if>
					
					<div class="card shadow mb-4">
						<div class="card-header py-3 mx-0 row justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">Datos actuales</h6>
							</a>
						</div>
						
						<div>
						</div>
						
						<div class="card-body">
							<div class="table-responsive">
								<table class="table text-center" id="" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th>Tipo de gasto</th>
											<th>Monto</th>
										</tr>
									</thead>
									<tbody>
											<tr>
												<td><c:out value="${gastos.tipoDeGasto}" /></td>
												<td>$ <c:out value="${gastos.monto}" /></td>
											</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
					<div class="container" style="max-width: 720px !important;">
						<div class="card o-hidden border-0 shadow-lg my-5">
							<div class="card-body p-0">
								<!-- Nested Row within Card Body -->
								<div class="row">
									<div class="col-lg-12">
										<div class="p-5">
											<div class="text-center">
												<h1 class="h4 text-gray-900 mb-4">Datos a modificar</h1>
											</div>
											
											<c:if test="${not empty error}">
												<span class="text-danger" style="float: left;">${error}</span>
												<br>
											</c:if>
											<c:if test="${not empty mensaje}">
												<span class="text-success" style="float: left;">${mensaje}</span>
												<br>
											</c:if>
											
											<form:form method="POST" modelAttribute="gastosNuevos" action="validarcambiosenestadistica?id=${gastos.id}" class="user">
												<div class="form-group row">
													<div class="col-sm-1">
														<label class="pt-2">$</label>
													</div>
													<div class="col-sm-11">
														<form:input path="monto" type="number" id="monto" name="monto"
														class="form-control form-control-user" placeholder="Monto a modificar" />
													</div>
												</div>
												<div class="form-group row">
													<div class="col-sm-12">
													    <form:select path="tipoDeGasto" id="tipoDeGasto" name="tipoDeGasto" class="form-control">
													    	<option value=""></option>
													    	<c:forEach items="${tipoDeGastos}" var="tipoGastos">
													    		<option value="${tipoGastos.nombre}">${tipoGastos.nombre}</option>
													    	</c:forEach>
													    </form:select>
													</div>
												</div>
												<button class="btn btn-primary btn-user btn-block text-white" type="submit">Modificar</button>
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