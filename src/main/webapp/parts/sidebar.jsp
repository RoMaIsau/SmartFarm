<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="${contextPath}/indexAdmin">
		<div class="sidebar-brand-icon">
			<i class="fas fa-warehouse"></i>
		</div>
		<div class="sidebar-brand-text mx-3">Smart Farm</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">


	<!-- Nav Item - Dashboard -->
	<li class="nav-item active">
		<!-- Segun cual sea el rol del usuario se muestran diferentes secciones del sidebar -->
		<c:choose>
			<c:when test="${ROL == 'Admin'}">
				<a class="nav-link" href="${contextPath}/usuarios"><i
					class="fas fa-user"></i> <span>Usuarios/as</span> </a>
			</c:when>
			<c:when test="${ROL == 'Veterinario'}">
				<a class="nav-link" href="${contextPath}/historiaClinica"><i
					class="fab fa-sticker-mule"></i> <span>Historiales clínicos</span> </a>
			</c:when>
			<c:when test="${ROL == 'Empleado'}">
				<a class="nav-link" href="${contextPath}/animales"><i
					class="fab fa-sticker-mule"></i> <span>Animales</span> </a>
			</c:when>
		</c:choose>
	</li>

	<!-- Nav Item - Dashboard -->
	<c:choose>
		<c:when test="${ROL == 'Admin'}">
			<!-- Divider -->
			<li class="nav-item">
				<hr class="sidebar-divider">
				<a class="nav-link" href="${contextPath}/gastos">
					<i class="fas fa-dollar-sign"></i>
					<span>Gastos</span>
				</a>
			</li>
		</c:when>
		<c:when test="${ROL == 'Veterinario'}">
			<!-- Divider -->
			<li class="nav-item">
				<hr class="sidebar-divider">
				<a class="nav-link" href="${contextPath}/enfermedades">
					<i class="fas fa-user"></i>
					<span>Historial de enfermedades</span>
				</a>
			</li>
		</c:when>
		<c:when test="${ROL == 'Empleado'}">
			<!-- Divider -->
			<li class="nav-item">
				<hr class="sidebar-divider">
				<a class="nav-link" href="${contextPath}/stock">
					<i class="fas fa-boxes"></i>
					<span>Stock</span>
				</a>
			</li>
		</c:when>
	</c:choose>


	<!-- Nav Item - Dashboard -->
	<c:choose>
		<c:when test="${ROL == 'Admin'}">
			<li class="nav-item">
				<hr class="sidebar-divider">
				<a class="nav-link" href="${contextPath}/estadisticas"> <i
					class="fas fa-chart-pie"></i> <span>Estadísticas</span>
				</a>
			</li>
		</c:when>
		<c:when test="${ROL == 'Veterinario'}">
			<li class="nav-item">
				<hr class="sidebar-divider">
				<a class="nav-link" href="#"> <i
					class="fas fa-user"></i> <span>PARA HABILITAR</span>
				</a>
			</li>
		</c:when>
	</c:choose>


	<!-- Nav Item - Dashboard -->
	<c:choose>
		<c:when test="${ROL == 'Admin' || ROL == 'Empleado'}">
			<li class="nav-item">
				<hr class="sidebar-divider d-none d-md-block">
				<a class="nav-link" href="${contextPath}/mapa">
					<i class="fas fa-map-marked-alt"></i>
					<span>Mapa</span>
				</a>
			</li>
			<li class="nav-item">
				<hr class="sidebar-divider d-none d-md-block">
				<a class="nav-link" href="${contextPath}/ubicaciones/mapa">
					<i class="fas fa-map-marked-alt"></i>
					<span>Ubicación en tiempo real</span>
				</a>
			</li>
		</c:when>
	</c:choose>

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>