$(document).ready(function() {

    /*============ Abrir e fechar os Modal's ============ */

    $('.menuItem').mouseover(function() {
        $('#header').css('border-bottom', '10px solid white');
    });

    $('.menuItem').mouseout(function() {
        $('#header').css('border-bottom', '10px solid #8837F0');
    });

    /*============ Clique para pesquisar perguntas ============ */

    $("#btn-pesq").click(function() {
        var desc = $('#busc-perg').val();
        var url = "http://localhost:3000/perguntas/pesquisar/" + desc + "";

        $.getJSON(url, function(result) {
            var perg = result;
            var out = "";

            if(perg.length > 0){
                for (var i = 0; i < perg.length; i++)
                    out += `<div class="pergunta `+perg[i].respondida+`" idperg="`+perg[i].idPergunta+`">`+perg[i].descricao+`</div>`;}
            else{
                out = "<h3> Sem resultados para sua busca :( </h3>";}
            
            $('#perguntas').html(out);
        });
    });

    $("#btn-pesq").click(function() {
        $("#pergBusc").html("Resultados da sua pesquisa:");
    })
})

function tracinhos(telefone){
    if(telefone.value.length == 5)
        telefone.value = telefone.value + '-';
}