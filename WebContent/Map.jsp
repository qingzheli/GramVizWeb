

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
    cb.getPath();
    cb.getmotif();
	cb.clear();

%>


<body>

	<div id="map" style="width: 600px; height: 400px"></div>



	<script src="leaflet.js"></script>

	<script>

	
	
        //var lat=[['${cb.returnLat()}', '${cb.returnLon()}'],['${cb.returnLat()}', '${cb.returnLon()}']];
        // var lat = new Array(8); 
        // var index=0;
        // for	(index = 0; index < 8; index++) {
        	    
        //	}
         
        lat= [['${cb.returnLat()}', '${cb.returnLon()}'],
              ['${cb.returnLat()}', '${cb.returnLon()}'],
			  ['${cb.returnLat()}', '${cb.returnLon()}'],
              ['${cb.returnLat()}', '${cb.returnLon()}'],
			  ['${cb.returnLat()}', '${cb.returnLon()}'],
			  ['${cb.returnLat()}', '${cb.returnLon()}'],
			  ['${cb.returnLat()}', '${cb.returnLon()}']
			  			  

        ];
        
        
        lat2= [['${cb.returnLat2()}', '${cb.returnLon2()}'],
              ['${cb.returnLat2()}', '${cb.returnLon2()}'],
			  ['${cb.returnLat2()}', '${cb.returnLon2()}'],
              ['${cb.returnLat2()}', '${cb.returnLon2()}'],
			  ['${cb.returnLat2()}', '${cb.returnLon2()}'],
			  ['${cb.returnLat2()}', '${cb.returnLon2()}'],
			  ['${cb.returnLat2()}', '${cb.returnLon2()}']
			  			  

        ];
        alert(lat.join('\n'));
        var s='${cb.getIntval()}';
        var s2=s.toString();
         

		var map = L.map('map').setView(lat[0], 13);

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



		L.circle(lat[0], 1, {

			color: 'red',

			fillColor: '#f03',

			fillOpacity: 0.5

		}).addTo(map).bindPopup("I am circle 1.");

		L.circle(lat[1], 1, {

			color: 'red',

			fillColor: '#f03',

			fillOpacity: 0.5

		}).addTo(map).bindPopup("I am circle 2.");

		L.circle(lat[2], 1, {

			color: 'red',

			fillColor: '#f03',

			fillOpacity: 0.5

		}).addTo(map).bindPopup("I am circle 3.");

				L.circle(lat[3], 1, {

			color: 'red',

			fillColor: '#f03',

			fillOpacity: 0.5

		}).addTo(map).bindPopup("I am circle 4.");

		
		L.circle(lat[4], 1, {

			color: 'red',

			fillColor: '#f03',

			fillOpacity: 0.5

		}).addTo(map).bindPopup("I am circle 5.");

		L.circle(lat[5], 1, {

			color: 'red',

			fillColor: '#f03',

			fillOpacity: 0.5

		}).addTo(map).bindPopup("I am circle 6.");

		L.circle(lat[6], 1, {

			color: 'red',

			fillColor: '#f03',

			fillOpacity: 0.5

		}).addTo(map).bindPopup("I am circle 7.");
		
		var pointA = new L.LatLng(lat[0][0], lat[0][1]);
        var pointB = new L.LatLng(lat[1][0], lat[1][1]);
		var pointC = new L.LatLng(lat[2][0], lat[2][1]);
        var pointD = new L.LatLng(lat[3][0], lat[3][1]);
		var pointE = new L.LatLng(lat[4][0], lat[4][1]);
        var pointF = new L.LatLng(lat[5][0], lat[5][1]);
		var pointG = new L.LatLng(lat[6][0], lat[6][1]);

var pointList = [pointA, pointB, pointC, pointD,pointE,pointF,pointG];




var firstpolyline = new L.Polyline(pointList, {
color: 'blue',
weight: 3,
opacity: 0.5,
smoothFactor: 1

});
firstpolyline.addTo(map);








L.circle(lat2[0], 1, {

	color: 'blue',

	fillColor: '#f03',

	fillOpacity: 0.5

}).addTo(map).bindPopup("I am circle 1.");

L.circle(lat2[1], 1, {

	color: 'blue',

	fillColor: '#f03',

	fillOpacity: 0.5

}).addTo(map).bindPopup("I am circle 2.");

L.circle(lat2[2], 1, {

	color: 'blue',

	fillColor: '#f03',

	fillOpacity: 0.5

}).addTo(map).bindPopup("I am circle 3.");

		L.circle(lat2[3], 1, {

	color: 'blue',

	fillColor: '#f03',

	fillOpacity: 0.5

}).addTo(map).bindPopup("I am circle 4.");


L.circle(lat2[4], 1, {

	color: 'blue',

	fillColor: '#f03',

	fillOpacity: 0.5

}).addTo(map).bindPopup("I am circle 5.");

L.circle(lat2[5], 1, {

	color: 'blue',

	fillColor: '#f03',

	fillOpacity: 0.5

}).addTo(map).bindPopup("I am circle 6.");

L.circle(lat2[6], 1, {

	color: 'blue',

	fillColor: '#f03',

	fillOpacity: 0.5

}).addTo(map).bindPopup("I am circle 7.");

var point1 = new L.latLng(lat2[0][0], lat2[0][1]);
var point2 = new L.LatLng(lat2[1][0], lat2[1][1]);
var point3 = new L.LatLng(lat2[2][0], lat2[2][1]);
var point4 = new L.LatLng(lat2[3][0], lat2[3][1]);
var point5 = new L.LatLng(lat2[4][0], lat2[4][1]);
var point6 = new L.LatLng(lat2[5][0], lat2[5][1]);
var point7 = new L.LatLng(lat2[6][0], lat2[6][1]);

var pointList = [point1, point2, point3, point4,point5,point6,point7];




var firstpolyline = new L.Polyline(pointList, {
color: 'red',
weight: 3,
opacity: 0.5,
smoothFactor: 1

});
firstpolyline.addTo(map);










//pointA = new L.LatLng(lat[1][0],lat[1][1]);
//pointB = new L.LatLng(lat[2][0],lat[2][1]);
//pointList = [pointA, pointB];

//firstpolyline = new L.polyline(lat, {
//color: 'red',
//weight: 3,
//opacity: 0.5,
//smoothFactor: 1

//});
//firstpolyline.addTo(map);


// zoom the map to the polyline
//map.fitBounds(polyline.getBounds());

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
