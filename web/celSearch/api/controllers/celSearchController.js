'use strict';

var mongoose = require('mongoose'),
	CelSearch = mongoose.model('CelSearch'); 

// Just testing things out 

exports.answerQuery = function(req, res) {
	var options = { new: true, setDefaultsOnInsert: true }; 

	// Find the document and update it otherwise create it 
	CelSearch.findOneAndUpdate(req.params.number, req.body, options, function(err, cel) {
		if (err) 
			res.send(err);
		res.json(cel);
	});
};
