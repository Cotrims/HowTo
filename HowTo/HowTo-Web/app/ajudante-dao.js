class AjudanteDao {

    // contrutor que vai receber uma instância da conexão do BD chamada db     
    constructor(db) {
        this._db = new db.Request();
    }

    //Método para cadastrar um novo ajudante
    cadastrar(ajudante, callback) {
        //Guardamos os valores em suas respectivas variáveis
        var email = ajudante.email;
        var senha = ajudante.senha;
        var nome = ajudante.nome;
        var senhaConf = ajudante.senhaConf;

        //Verificações 
        if (senha != "" && nome != "" && senhaConf != "" && email != "")//Se todos os valores foram digitados
            if (senha == senhaConf) //Se as senhas correspondem
                this._db.query("insert into ajudante values ('" + nome + "','" + email + "','" + senha + "')", function(err, recordset) { //Envio do método para o BD
                    callback(err, recordset);
                });
    }
}

module.exports = AjudanteDao; //Exportação da classe