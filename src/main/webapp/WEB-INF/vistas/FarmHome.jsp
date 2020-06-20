<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	    		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		
  <script src="gmaps.js"></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	 <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
	
	</head>
	<body>
	<p id="boton">click aqui</p>
	
	<div id="mapa"></div>
	 
            <div id='map_canvas' style='width:100%; height:400px;'></div>
       
	<script>
	
	 var map;
    var latitud ="-34.584673";
   var longitud ="-58.393610";
     
     $(document).ready(function() {
         localizar();    
     });
     
  
     function coordenadas(position) {
    	 
         latitud=position.coords.latitude; 
        longitud=position.coords.longitude; 
         cargarMapa();
     }
     
 
     
     
     
     function localizar() {
         if (navigator.geolocation) { 
             navigator.geolocation.getCurrentPosition(coordenadas);
             cargarMapa();
         }else{
             alert('Tu navegador no soporta geolocalización!');
         }
     }
     
     function cargarMapa() {
         var latlon = new google.maps.LatLng(latitud,longitud); 
         var myOptions = {
             zoom: 17,
             center: latlon,
             mapTypeId: google.maps.MapTypeId.ROADMAP
         };
         map = new google.maps.Map($("#map_canvas").get(0), myOptions);
         var coorMarcador = new google.maps.LatLng(latitud,longitud); 
             
         var marcador = new google.maps.Marker({
             position: coorMarcador,
             map: map, 
             title: "Ganado, posicion" 
         });
     }
     
    
     
   
				
			
    
	
	
	
	</script>
		

		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>