'use strict';

var mongoose = require('mongoose'),
	CelSearch = mongoose.model('CelSearch'); 

// Just testing things out 
exports.answerQuery = function(req, res) {
	CelSearch.find({}, function(err, cel) {
		if (err) 
			res.send(err);
		res.json({message: 'answerQuery called'});
	});
};


exports.addSubject = function(req, res) {
	CelSearch.find({}, function(err, cel) {
		if (err) 
			res.send(err);
		res.json(cel);
	});
};


exports.updateSubject = function(req, res) {
	CelSearch.find({}, function(err, cel) {
		if (err)
			res.send(err);
		res.json(cel); 
	});
};
