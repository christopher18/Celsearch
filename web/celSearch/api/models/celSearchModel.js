'use strict';
var mongoose = require('mongoose'); 
var Schema = mongoose.Schema; 

var RequestSchema = new Schema({
	name: {
		type: Number, 
		Required: 'Number of incoming request'
	}, 
	subject: {
		type: String,
		default: ''
	}
});

module.exports = mongoose.model('CelSearch', RequestSchema)