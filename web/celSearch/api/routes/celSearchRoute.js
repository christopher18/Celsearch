'use strict';

module.exports = function(app) {
	var celRequest = require('../controllers/celSearchController'); 

	// Routes 
	app.route('/celsearch/wikipedia/:number')
		.post(celRequest.wikipediaQuery);

	// Route for Mitsuku
	app.route('/celsearch/mitsuku/:number')
		.post(celRequest.mitsukuQuery); 
};