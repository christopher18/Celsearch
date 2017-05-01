'use strict';

var mongoose = require('mongoose'),
	CelSearch = mongoose.model('CelSearch'); 

// Use python-shell so that we can run query_wikipedia.py script for Wikipedia summary
var PythonShell = require('python-shell');

// Just testing things out 

// Leaving it nested for now because of issues with findOneAndUpdate
exports.wikipediaQuery = function(req, res) {
	var options = { new: true, setDefaultsOnInsert: true }; 

	// Find the document and update it otherwise create it 
	CelSearch.findOneAndUpdate(req.params.number, req.body, options, function(err, cel) {
		if (err) 
			res.send(err);

		// Add the req in the DB if it isn't already there 
		if (!cel) {
			var cel = new CelSearch(req.body);
			cel.save(function(err, cel) {
				if (err)
					res.send(err);
			});
		}

		var pyOptions = {
			scriptPath: './api/scripts',
			args: [req.body.query]
		}; 

		PythonShell.run('query_wikipedia.py', pyOptions, function(err, result) {
			if (err) {
				console.log('ERROR');
				throw err;
			}
			console.log('result is : ' + result);  
			res.json(result); 
		});
	});
};


exports.mitsukuQuery = function(req, res) {
	var options = { new: true, setDefaultsOnInsert: true }; 

	// Find the document and update it otherwise create it 
	CelSearch.findOneAndUpdate(req.params.number, req.body, options, function(err, cel) {
		if (err) 
			res.send(err);

		// Add the req in the DB if it isn't already there 
		if (!cel) {
			var cel = new CelSearch(req.body);
			cel.save(function(err, cel) {
				if (err)
					res.send(err);
			});
		}

		var pyOptions = {
			scriptPath: './api/scripts',
			args: [req.body.query]
		}; 

		PythonShell.run('query_mitsuku.py', pyOptions, function(err, result) {
			if (err) {
				console.log('ERROR');
				throw err;
			}
			console.log('result is : ' + result);  
			res.json(result); 
		});
	});
};
