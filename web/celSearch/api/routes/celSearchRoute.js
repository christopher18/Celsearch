'use strict';

module.exports = function(app) {
	var celRequest = require('../controllers/celSearchController'); 

	// Routes 
	app.route('/celsearch/:number')
		.post(celRequest.answerQuery);
};