var mssql = require('mssql');

const config = {
    user: 'BD19040',
    password: 'Cotrimpd19',
    server: 'regulus.cotuca.unicamp.br',
    database: 'BD19040'
};

mssql.connect(config)
    .then(conexao => global.conexao = conexao)
    .catch(erro => console.log(erro));

module.exports = mssql;