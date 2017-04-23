'use strict';

module.exports = function(app) {
	var celRequest = require('../controllers/celSearchController'); 

	// Routes 
	app.route('/celsearch/:query')
		.get(celRequest.answerQuery);

	app.route('/celsearch/:number')
		.post(celRequest.addSubject)
		.put(celRequest.updateSubject);
};