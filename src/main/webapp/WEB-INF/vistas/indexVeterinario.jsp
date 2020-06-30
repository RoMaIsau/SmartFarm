<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
					<h1>Lista de animales:</h1><br>



  <c:forEach items="${vencidos}" var="v">
  
				    	 
				  <h3 class="text-danger">"El animal  ${v.id} tiene vacunas vencidas"
						<a class="text-primary" href="detalle?id=${v.id}">Ver</a><br>	
					</h3>
					 </br>		
					
							 
</c:forEach>



 <c:forEach items="${vencidas}" var="v">
  
				    	 
				  <h3 class="text-danger">"Vacunas vencidas: ${v.nombre}"
							<a href="vacunar?id=${vacaId}&nombre=${v.nombre}">Vacunar</a><br>
					</h3> </br>		
							
							 
</c:forEach><BR>


<h3>${mensaje}</h3><br>

<h3><a href="/SmartFarm/listaGanado" >MONITOREO DE SIGNOS VITALES</a></h3><BR>
<h3><a href="/SmartFarm/historiaClinica" >HISTORIAS CLINICAS</a></h3><BR>
<a href="/SmartFarm/indexVeterinario" ><h3>${volver}</h3></a><br>					


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
	<%@ include file="../../parts/modalCerrarSesion.jsp" %>
	
	<!-- Bootstrap core JavaScript-->
	<%@ include file="../../parts/scripts.jsp" %>
	
</body>
</html>