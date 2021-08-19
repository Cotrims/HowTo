var express = require('express');
var app = express();

var bodyParser = require('body-parser');
var expressLayouts = require('express-ejs-layouts');

const rotas = require('../app/rotas.js');

app.set('view engine', 'ejs');

// informar o express que vamos usar os módulos relacionados às variaveis abaixo 
app.use(expressLayouts);
app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.static('public'));

// passar como parâmetro para o módulo rotas.js
rotas(app);

module.exports = app;