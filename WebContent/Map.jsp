

<!DOCTYPE html>

<html>

<head>
	<title>Leaflet Viz Demo</title>

	<meta charset="utf-8" />



	<meta name="viewport" content="width=device-width, initial-scale=1.0">



	<link rel="stylesheet" href="leaflet.css" />

</head>


<jsp:useBean id="cb" scope="session" class="test.ColorGameBean" />
<jsp:setProperty name="cb" property="*" />

<%
    cb.processRequest();
    cb.getPath();
    cb.getmotif();
    cb.clear();
%>


<body>

	<div id="map" style="width: 600px; height: 400px"></div>



	<script src="leaflet.js"></script>

	<script>

        var lat=[['${cb.returnLat()}', '${cb.returnLon()}'],['${cb.returnLat()}', '${cb.returnLon()}']];
          var s='${cb.getIntval()}';
          var s2=s.toString();
          alert(lat.join('\n'));

		var map = L.map('map').setView([40.001,116.3], 13);

        var x='${cb.getColor2()}';

		L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6IjZjNmRjNzk3ZmE2MTcwOTEwMGY0MzU3YjUzOWFmNWZhIn0.Y8bhBaUMqFiPrDRW9hieoQ', {

			maxZoom: 18,

			attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +

				'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +

				'Imagery © <a href="http://mapbox.com">Mapbox</a>',

			id: 'mapbox.streets'

		}).addTo(map);




		L.marker([40.001,116.3]).addTo(map)

			.bindPopup(x).openPopup();



		L.circle(lat[0], 100, {

			color: 'red',

			fillColor: '#f03',

			fillOpacity: 0.5

		}).addTo(map).bindPopup("I am circle 1.");

		L.circle(lat[1], 100, {

			color: 'red',

			fillColor: '#f03',

			fillOpacity: 0.5

		}).addTo(map).bindPopup("I am circle 2.");


               var polyline = L.polyline(lat, {color: 'blue'}).addTo(map);
// zoom the map to the polyline
map.fitBounds(polyline.getBounds());

		var popup = L.popup();



		function onMapClick(e) {

			popup

				.setLatLng(e.latlng)

				.setContent("You clicked the map at " + e.latlng.toString())

				.openOn(map);

		}



		map.on('click', onMapClick);



	</script>

</body>

</html>
