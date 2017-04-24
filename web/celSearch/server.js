var express = require('express'), 
  app = express(),
  port = process.env.PORT || 3000, 
  mongoose = require('mongoose'),
  CelSearch = require('./api/models/celSearchModel'), 
  bodyParser = require('body-parser');

mongoose.Promise = global.Promise; 
mongoose.connect('mongodb://localhost/CelSearchDB');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var routes = require('./api/routes/celSearchRoute');
routes(app);

app.listen(port); 

console.log('CelSearch RESTful API server started on: ' + port);


