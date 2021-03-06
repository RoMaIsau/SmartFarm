
<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop"
		class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">

		<!-- Nav Item - Alerts -->
		<li class="nav-item dropdown no-arrow mx-1"><a
			class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> <!-- Counter - Alerts -->
				<span class="badge badge-danger badge-counter" id="contador"></span>
		</a> <!-- Dropdown - Alerts -->


			<div
				class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
				aria-labelledby="alertsDropdown" id="notificaciones">
				<h6 class="dropdown-header">Notificaciones</h6>
				<c:if test="${not empty notificaciones}">
					<c:forEach items="${notificaciones}" var="noti">
						<c:choose>
							<c:when test="${noti.notificacion.estado == true}">
								<a class="dropdown-item d-flex align-items-center text-gray-500"
									href="actualizarNotificacion?id=<c:out
									value="${noti.notificacion.id}" />">
							</c:when>
							<c:otherwise>
								<a class="dropdown-item d-flex align-items-center font-weight-bold" href="actualizarNotificacion?id=<c:out
									value="${noti.notificacion.id}"/>">
							</c:otherwise>
						</c:choose>
						<div class="mr-3">
							<div class="icon-circle bg-primary">
								<i class="fas fa-file-alt text-white"></i>
							</div>
						</div>
						<div>
							<div class="small text-gray-500">
								<c:out value="${noti.notificacion.fecha}" />
							</div>
							<span class="font-weight-bold"><c:out
									value="${noti.notificacion.titulo}" /></span> <br /> <span><c:out
									value="${noti.notificacion.detalles}" /></span>
						</div>
						</a>
					</c:forEach>
				</c:if>
				<a class="dropdown-item text-center small text-gray-500" href="#">Mostrar
					todas las alertas</a>

			</div></li>

		<!-- Nav Item - User Information -->
		<li class="nav-item dropdown no-arrow"><a
			class="nav-link dropdown-toggle" href="#" id="userDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <img class="img-profile rounded-circle"
				src="<c:url value="/img/avatar.svg"/>">
		</a> <!-- Dropdown - User Information -->
			<div
				class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
				aria-labelledby="userDropdown">
				<a class="dropdown-item" href="#"> <i
					class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="#" data-toggle="modal"
					data-target="#logoutModal"> <i
					class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
					Salir
				</a>
			</div></li>

	</ul>

</nav>