'use strict';

ymaps.ready(function () {
	var myMap = new ymaps.Map('YMapsID', {
		center: [50.44746356, 30.52398682],
		zoom: 10,
		behaviors: ['default', 'scrollZoom']
	}),
	// Создание кнопки определения местоположения
	geolocationButton = new GeolocationButton({
		data: {
			image: 'wifi.png',
			title: 'Определить местоположение'
		}
	}, {
		// Зададим опции для кнопки.
		selectOnClick: false
	});

	myMap.controls
	.add('mapTools')
	.add(new CrossControl)
	.add(geolocationButton, { top: 5, left: 100 })
	.add('zoomControl')
	.add('typeSelector', { top: 5, right: 5 })
	.add(new ymaps.control.SearchControl({ noPlacemark: true }), { top: 5, left: 200 });

	new LocationTool(myMap);
});