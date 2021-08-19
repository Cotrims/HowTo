//Importações das classes que serão ultilizadas
var conexao = require('../config/custom-mssql');
var AjudanteDao = require('../app/ajudante-dao');
var PerguntaDao = require('../app/pergunta-dao');
var RespostaDao = require('../app/resposta-dao');

module.exports = (app) => {

    //Rota padrão/inicial
    app.get('/', function(req, resp) {
        resp.render("paginas/home") //Renderiza a Home
    });

    //Função que excutará os comandos para o BD e retonará valores para a página
    function execSQL(sql, resposta) {
        global.conexao.request()
            .query(sql)
            .then(resultado => resposta.json(resultado.recordset))
            .catch(erro => resposta.json(erro));
    }

    //Rota de exibição perguntas
    app.get('/perguntas', function(req, resp) {

        const perguntaDao = new PerguntaDao(conexao); //Instânciação da conexão

        perguntaDao.listaFreq(function(erro, resultados) { //Após selecionar as perguntas por sua frequência
            resp.render('paginas/perguntas', {
                lista: resultados["recordset"] //Renderiza a pagina de perguntas passando um array com os resultados do select
            })
        });
    });

    //Rota de exibição respostas
    app.get('/respostas', function(req, resp) {

        const perguntaDao = new PerguntaDao(conexao); //Instânciação da conexão

        perguntaDao.listaFreq(function(erro, resultados) { //Após selecionar as perguntas por sua frequência 
            resp.render('paginas/respostas', {
                lista: resultados["recordset"] //Renderiza a pagina de perguntas passando um array com os resultados do select

                //Embora esta rota também execute "perguntaDao.listaFreq()", no corpo da pagina sua aparencia será outra
            })
        });
    });

    //Rota de inserção de respostas
    app.post('/respostas/inserir', function(request, response) {

        const respostaDao = new RespostaDao(conexao); //Instânciação da conexão

        respostaDao.adiciona(request.body, function(erro) { //Chamada do método para adicionar a resposta
            if (erro) {
                console.log("erro na inclusão"); //Caso ocorra algum erro ele será printado no console
            } else {
                response.redirect('/respostas'); //Caso não, renderiza a pagina de respostas novamente
            }
        });
    });

    //Rota de inserção de perguntas
    app.post('/perguntas/inserir', function(request, response) {

        const perguntaDao = new PerguntaDao(conexao); //Instânciação da conexão

        perguntaDao.adiciona(request.body, function(erro) { //Chamada do método para adicionar a pergunta
            if (erro) {
                console.log("erro na inclusão"); //Caso ocorra algum erro ele será printado no console
            } else {
                response.redirect('/perguntas'); //Caso não, renderiza a pagina de perguntas novamente
            }
        });
    });

    //Rota de cadastro de ajudantes
    app.post('/ajudantes/cadastrar', function(request, response) {

        const ajudanteDao = new AjudanteDao(conexao); //Instânciação da conexão

        ajudanteDao.cadastrar(request.body, function(erro) { //Chamada do método para cadastrar um novo ajudante
            if (erro) {
                console.log("erro no cadastro"); //Caso ocorra algum erro ele será printado no console
            } else {
                response.redirect('/respostas'); //Caso não, renderiza a pagina de respostas novamente
            }
        });
    });

    //Para procurar por alguma pergunta por sua descrição, esse método
    //executa uma Stored Procedure que retorna os campos necessários
    app.get('/perguntas/pesquisar/:desc', function(req, resp) {
        var desc = req.params.desc;
        execSQL("pesq_sp '" + desc + "'", resp);
    });

    //Para procurar por alguma pergunta por seu id, esse método executa 
    //uma Stored Procedure que retorna a resposta da pergunta selecionada
    app.get('/perguntas/:id?', function(req, resp) {
        let filtro = '';
        if (req.params.id)
            filtro = " " + parseInt(req.params.id);
        execSQL('sp_Resperg' + filtro, resp);
    });

    //Para procurar por alguma resposta por seu id, esse método 
    //retorna os campos necessários para poder enviar uma resposta
    app.get('/respostas/:id?', function(req, resp) {
        let filtro = '';
        if (req.params.id)
            filtro = " " + parseInt(req.params.id);
        execSQL('select idPergunta, descricao, dataPergunta from Pergunta where idPergunta =' + filtro, resp);
    });
}