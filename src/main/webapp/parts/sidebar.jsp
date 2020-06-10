<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="indexAdmin">
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
				<a class="nav-link" href="usuarios"><i class="fas fa-user"></i>
					<span>Usuarios/as</span> </a>
			</c:when>
			<c:when test="${ROL == 'Veterinario'}">
				<a class="nav-link" href="animales"><i
					class="fab fa-sticker-mule"></i> <span>Animales</span> </a>
			</c:when>
			<c:when test="${ROL == 'Empleado'}">
				<a class="nav-link" href="animales"><i
					class="fab fa-sticker-mule"></i> <span>Animales</span> </a>
			</c:when>
		</c:choose>
	</li>

	<!-- Nav Item - Dashboard -->
	<li class="nav-item"><c:choose>
			<c:when test="${ROL == 'Veterinario'}">
				<!-- Divider -->
				<hr class="sidebar-divider">
				<a class="nav-link" href="seccion2"> <i class="fas fa-user"></i>
					<span>Seccion 2</span>
				</a>
			</c:when>
			<c:when test="${ROL == 'Empleado'}">
				<!-- Divider -->
				<hr class="sidebar-divider">
				<a class="nav-link" href="stock"><i class="fas fa-boxes"></i> <span>Stock</span>
				</a>
			</c:when>
		</c:choose></li>


	<!-- Nav Item - Dashboard -->
	<li class="nav-item"><c:choose>
			<c:when test="${ROL == 'Admin'}">
				<!-- Divider -->
				<hr class="sidebar-divider">
				<a class="nav-link" href="estadisticas"> <i
					class="fas fa-chart-pie"></i> <span>Estadísticas</span>
				</a>
			</c:when>
			<c:when test="${ROL == 'Veterinario'}">
				<!-- Divider -->
				<hr class="sidebar-divider">
				<a class="nav-link" href="seccion3"> <i class="fas fa-user"></i>
					<span>Seccion 3</span>
				</a>
			</c:when>
		</c:choose></li>


	<!-- Nav Item - Dashboard -->
	<li class="nav-item">
			<c:choose>
				<c:when test="${ROL == 'Veterinario' or ROL == 'Empleado'}">
	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">
					<a class="nav-link" href="mapa">
					<i class="fas fa-map-marked-alt"></i>
					<span>Mapa</span>
				</c:when>
			</c:choose>
		</a>
	</li>

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>