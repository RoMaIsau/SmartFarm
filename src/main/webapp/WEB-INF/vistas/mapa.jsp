<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>

<%@ include file="../../parts/meta.jsp"%>
<%@ include file="../../parts/map.jsp"%>

</head>
<body id="page-top">
<!-- FUNCIÓN PARA MOSTRAR MAPA -->
<script>
	function myFunction() {
		var tblData = "";
		var tblArr = new Array();
		var counter=0;
		tblLength = document.getElementById("ddReferences").rows.length;
		var locations = new Array(tblLength);
		for (i = 0; i < tblLength; i++) {
			locations[i]=new Array(document.getElementById("ddReferences").rows[i].cells.length);			
			for (j = 0; j < document.getElementById("ddReferences").rows[i].cells.length; j++) {
				locations[i][j] = document.getElementById("ddReferences").rows[i].cells[j].innerText;
			    counter++;
			}
		}		
		
		var iconURLPrefix = 'http://maps.google.com/mapfiles/ms/icons/';
		var icons = [ iconURLPrefix + 'red-dot.png',
				iconURLPrefix + 'green-dot.png',
				iconURLPrefix + 'blue-dot.png',
				iconURLPrefix + 'orange-dot.png',
				iconURLPrefix + 'purple-dot.png',
				iconURLPrefix + 'pink-dot.png',
				iconURLPrefix + 'yellow-dot.png' ]
		var map = new google.maps.Map(document.getElementById('map'), {
			zoom : 10,
			center : new google.maps.LatLng(-34.624646, -58.671073),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		});
		var infowindow = new google.maps.InfoWindow();
		var marker, i;
		var iconCounter = 0;
		for (i = 0; i < locations.length; i++) {
			marker = new google.maps.Marker({
				position : new google.maps.LatLng(locations[i][1],
						locations[i][2]),
				icon : icons[iconCounter++],
				map : map
			});
			google.maps.event.addListener(marker, 'click',
					(function(marker, i) {
						return function() {
							infowindow.setContent(locations[i][0]);
							infowindow.open(map, marker);
						}
					})(marker, i));
		}
	}
</script>

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
				
				
				
				
				
				
				
				
				Cantidad de resultados : ${fn:length(lists)-1}
				
				<h3>ID Animal &ensp; Latitude &ensp; Longitude &ensp; Nombre Animal</h3>

<table id='ddReferences'>
    <c:forEach items="${lists}" var="lists">
        <tr>
            <td>${lists.value.value}</td>
            <td>${lists.value.latitude}</td>
            <td>${lists.value.longitude}</td>
            <td>${lists.key}</td>
        </tr>
    </c:forEach>
</table>

	<span>
		<button id="w-button-show" type="button" onClick="myFunction()">Show on Map</button>
	</span>
	  <div id="map" style="height: 400px; width: 500px;">
				
				
				
				
				
				
				
				
				
				
				
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