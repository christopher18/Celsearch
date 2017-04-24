'use strict';

var mongoose = require('mongoose'),
	CelSearch = mongoose.model('CelSearch'); 

// Use python-shell so that we can run query_wikipedia.py script for Wikipedia summary
var PythonShell = require('python-shell');

// Just testing things out 

exports.answerQuery = function(req, res) {
	var options = { new: true, setDefaultsOnInsert: true }; 

	// Find the document and update it otherwise create it 
	CelSearch.findOneAndUpdate(req.params.number, req.body, options, function(err, cel) {
		if (err) {
			console.log('Error');
			res.send(err);
		}
			
		// Setup the options for PythonShell call
		var options = {
			mode: 'text',
			scriptPath: './api/scripts',
			args: ['Obama']
		};

		PythonShell.run('query_wikipedia.py', options, function(err, results) {
			if (err) {
				console.log('ERror');
				throw err;
			}
			console.log(results);
		})
		res.json(cel);
	});
};
