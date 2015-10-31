var idmRest = {
	weather: {
		getByZipCode: function (zipCode) {
			// TODO: Sacar referencia absoluta
			return jQuery.getJSON( "http://localhost:7001/customIDM/rest/weather/zipCode/" + zipCode);
		}
	}
};
