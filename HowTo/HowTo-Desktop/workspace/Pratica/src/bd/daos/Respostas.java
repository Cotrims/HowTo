package bd.daos;

import java.sql.*;
import bd.*;
import bd.dbos.*;
import bd.core.MeuResultSet;

/**
 * A classe Respostas representa a camada
 * l?gica de acesso a tabela resposta existente 
 * no banco de dados. Nela existem m?todos como 
 * inser??o e remo??o de novas respostas.
 * @author Vin?cius Martins Cotrim
 * @author Guilherme Jos? S. A.
 * @since 2019
 */

public class Respostas
{
	/**
	 * Verifica a exist?ncia de uma resposta no banco de dados,
	 * passando seu id como par?mero.
	 * @param id O id da resposta para verificar sua existencia no banco.
	 * @return Um valor boolean. Verdadeiro caso exista, Falso caso n?o esteja cadastrado.
	 * @throws SQLException Caso ocorra algum erro na busca pela resposta.
	 */
    public static boolean cadastrado (int id) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Resposta " +
                  "WHERE idResposta = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar resposta");
        }

        return retorno;
    }

    /**
     * Inclui uma nova resposta no banco de dados.
     * @param resposta A nova resposta a ser incluida. Eh uma instancia da classe
     * Resposta.
     * @throws Exception Caso a resposta a ser inserida passaca como parametro
     * seja nula.
     * @throws SQLException Caso ocorra erro na inser??o da resposta.
     */
    public static void incluir (Resposta resposta) throws Exception
    {
        if (resposta==null)
            throw new Exception ("Resposta nao fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO Resposta " +
                  "(DATARESPOSTA,DESCRICAO, VALIDADE, IDAJUDANTE, IDPERGUNTA) " +
                  "VALUES " +
                  "(NULL,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);
            
            BDSQLServer.COMANDO.setString (1, resposta.getDescricao ());
            BDSQLServer.COMANDO.setString (2, resposta.getValida () + "");
            BDSQLServer.COMANDO.setInt    (3, resposta.getIdAjudante());
            BDSQLServer.COMANDO.setInt    (4, resposta.getIdPergunta ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir resposta");
        }
    }

    /**
     * Exclui uma resposta ja existente no banco de dados.
     * @param id O id da resposta a ser excluida.
     * @throws Exception Caso a busca pela resposta usando o id
     * fornecido nao de resultado
     * @throws SQLException Caso ocorra erro na exclusao da resposta.
     */
    public static void excluir (int id) throws Exception
    {
        if (!cadastrado (id))
            throw new Exception ("Nao cadastrada");

        try
        {
            String sql;

            sql = "DELETE FROM RESPOSTA " +
                  "WHERE IDRESPOSTA=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao excluir resposta");
        }
    }

    /**
     * Altera uma resposta ja existente no banco de dados.
     * @param resposta A resposta a ser alterada. Eh uma instancia da classe
     * Resposta.
     * @throws Exception Caso a resposta a ser alterada nao seja passada como
     * par?metro.
     * @throws Exception Caso a resposta a ser alterado nao exista no 
     * banco de dados.
     * @throws SQLException Caso ocorra erro na alteracao da resposta.
     */
    public static void alterar (Resposta resposta) throws Exception
    {
        if (resposta==null)
            throw new Exception ("Resposta nao fornecida");

        if (!cadastrado (resposta.getIdResposta()))
            throw new Exception ("Nao cadastrada");

        try
        {
            String sql;

            sql = "UPDATE RESPOSTA " +
                  "SET DATARESPOSTA=?, " +
                  "DESCRICAO=?, " +
                  "VALIDADE=?, " +
                  "IDAJUDANTE=?, " +
                  "IDPERGUNTA=? " +
                  "WHERE IDRESPOSTA = ?";


            BDSQLServer.COMANDO.prepareStatement (sql);

			BDSQLServer.COMANDO.setDate   (1, resposta.getDataResposta ());
            BDSQLServer.COMANDO.setString (2, resposta.getDescricao ());
            BDSQLServer.COMANDO.setString   (3, resposta.getValida() + "");
            BDSQLServer.COMANDO.setInt    (4, resposta.getIdAjudante ());
            BDSQLServer.COMANDO.setInt    (5, resposta.getIdPergunta ());
			BDSQLServer.COMANDO.setInt    (6, resposta.getIdResposta ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao atualizar dados de resposta");
        }
    }

    /**
     * Retorna uma resposta existente no banco de dados, buscando
     * pelo seu id.
     * @param idResposta O id da resposta a ser buscada e retornada.
     * @return Caso exista, retorna uma inst?ncia da classe Resposta
     * semelhante a encontrada na pesquisa usando o id passado como 
     * parametro.
     * @throws Exception Caso a busca pela resposta nao de resultados.
     * @throws SQLException Caso ocorra erros na busca da resposta.
     */
    public static Resposta getResposta (int idResposta) throws Exception
    {
    	Resposta resposta = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM RESPOSTA " +
                  "WHERE IDRESPOSTA = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, idResposta);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrada");

            resposta = new Resposta (resultado.getInt   ("IDRESPOSTA"),
            						 resultado.getInt   ("IDPERGUNTA"),
            						 resultado.getInt   ("IDAJUDANTE"),
            						 resultado.getDate  ("DATARESPOSTA"),
            						 (resultado.getString  ("VALIDADE")).charAt(0),
                               		 resultado.getString("DESCRICAO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar resposta");
        }

        return resposta;
    }
    
    /**
     * Retorna uma resposta existente no banco de dados, buscando
     * pelo id da sua pergunta.
     * @param idPergunta O id da pergunta que a resposta possui.
     * @return Caso exista, retorna uma inst?ncia da classe Resposta
     * semelhante a encontrada na pesquisa.
     * @throws Exception Caso a busca pela resposta nao de resultados.
     * @throws SQLException Caso ocorra erros na busca da resposta.
     */
    public static Resposta getRespostaPorIdPergunta (int idPergunta) throws Exception
    {
    	Resposta resposta = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM RESPOSTA " +
                  "WHERE IDPERGUNTA = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt(1, idPergunta);
            

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
            

            if (!resultado.first())
                throw new Exception ("Nao cadastrada");

            resposta = new Resposta (resultado.getInt   ("IDRESPOSTA"),
            						 resultado.getInt   ("IDPERGUNTA"),
            						 resultado.getInt   ("IDAJUDANTE"),
            						 resultado.getDate  ("DATARESPOSTA"),
            						 (resultado.getString  ("VALIDADE")).charAt(0),
                               		 resultado.getString("DESCRICAO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar resposta");
        }

        return resposta;
    }
    
    /**
     * Busca todas as respostas invalidas existentes no banco de dados.
     * @return Uma instancia da classe MeuResultSet com todos
     * os resultados da busca.
     * @throws SQLException Caso ocorra erros na busca das respostas.
     */
    public static MeuResultSet getRespostasInvalidas () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM RESPOSTA WHERE VALIDADE = 'N'";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar respostas");
        }

        return resultado;
    }

    /**
     * Busca todas as respostas existentes no banco de dados.
     * @return Uma instancia da classe MeuResultSet com todos
     * os resultados da busca.
     * @throws SQLException Caso ocorra erros na busca das respostas.
     */
    public static MeuResultSet getRespostas () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM RESPOSTA";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar respostas");
        }

        return resultado;
    }
}