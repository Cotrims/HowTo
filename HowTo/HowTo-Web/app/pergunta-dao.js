class PerguntaDao {

    // contrutor que vai receber uma instância da conexão do BD chamada db     
    constructor(db) {
        this._db = new db.Request();
    }

    //Listagem de todas as perguntas existêntes no BD
    lista(callback) {
        this._db.query('select * from pergunta', function(err, recordset) {
            callback(err, recordset);
        });
    }

    //Para ver as perguntas mais frequentes. Uma listagem orderana pela frequência
    listaFreq(callback) {
        this._db.query('select * from pergunta order by frequencia desc', function(err, recordset) {
            callback(err, recordset);
        });
    }

    //Para que as perguntas não fiquem sem resposta por passarem despercebidas. Uma listagem por tempo de envio
    listaTempo(callback) {
        this._db.query('select * from pergunta order by dataPergunta', function(err, recordset) {
            callback(err, recordset);
        });
    }

    //Adição de uma pergunta no BD
    adiciona(pergunta, callback) {
        var desc = pergunta.desc;

        this._db.query("INSERT INTO Pergunta VALUES (null, '" + desc + "','N',1)", (err) => {
            console.log("Erro inserção de pergunta: " + err);
            callback(err);
        })
    }

    //Ao selecionar uma pergunta esse método buscará ela por seu id
    buscaPorId(id, callback) {
        this._db.query("SELECT * FROM PERGUNTA WHERE idPergunta='" + id + "'", (err, recordset) => {
            console.log("Erro na busca da pergunta: " + err);
            console.log("recordset: " + recordset.recordset);

            callback(err, recordset.recordset);
        })
    }

    //Método que será executado na busca de perguntas por sua descrição
    busca(descricao, callback) {
        var desc = descricao + "";
        this._db.query("pesq_sp " + desc, (err, recordset) => {
            console.log("Erro na busca da pergunta: " + err);
            console.log("recordset: " + recordset.recordset);

            callback(err, recordset.recordset);
        })
    }
}

module.exports = PerguntaDao; //Exportação da classe