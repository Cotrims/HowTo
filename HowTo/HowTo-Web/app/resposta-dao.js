class RespostaDao {

    // contrutor que vai receber uma instância da conexão do BD chamada db     
    constructor(db) {
        this._db = new db.Request();
    }

    //Listagem de todas as respostas no BD
    lista(callback) {
        this._db.query('select * from resposta', function(err, recordset) {
            callback(err, recordset);
        });
    }

    //Adição de uma pergunta
    adiciona(resposta, callback) {
        this._db.query("sp_insResp " + "'" + resposta.resp + "'" + ", " + resposta.idPerg, function(err, recordset) {
            callback(err, recordset);
        });
    }

    //Seleciona todas e apenas as perguntas que possuem uma resposta
    listaPergResp(callback) {
        this._db.query(`select p.idPergunta, p.descricao as '
            Pergunta ', r.idResposta, r.descricao as '
            Resposta '
            from Resposta r,
            Pergunta p where r.idPergunta = p.idPergunta`, function(err, recordset) {
            callback(err, recordset);
        });
    }
}

module.exports = RespostaDao; //Exportação da classe