'use strict';
var mongoose = require('mongoose'); 
var Schema = mongoose.Schema; 

var RequestSchema = new Schema({
	query: {
		type: String,
		default: ''
	},
	number: {
		type: String, 
		default: ''
	}, 
	subject: {
		type: String,
		default: ''
	}
});

module.exports = mongoose.model('CelSearch', RequestSchema)