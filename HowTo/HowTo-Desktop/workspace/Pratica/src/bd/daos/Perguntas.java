package bd.daos;

import java.sql.*;
import bd.*;
import bd.dbos.*;
import bd.core.MeuResultSet;

/**
 * A classe Perguntas representa a camada
 * l?gica de acesso a tabela pergunta existente 
 * no banco de dados. Nela existem m?todos como 
 * inser??o e remo??o de novas perguntas.
 * @author Vin?cius Martins Cotrim
 * @author Guilherme Jos? S. A.
 * @since 2019
 */

public class Perguntas
{
	/**
	 * Verifica a exist?ncia de uma pergunta no banco de dados,
	 * passando seu id como par?mero.
	 * @param id O id da pergunta para verificar sua existencia no banco.
	 * @return Um valor boolean. Verdadeiro caso exista, Falso caso n?o esteja cadastrado.
	 * @throws SQLException Caso ocorra algum erro na busca pela pergunta.
	 */
    public static boolean cadastrado (int id) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Pergunta " +
                  "WHERE idPergunta = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar pergunta");
        }

        return retorno;
    }
    
    /**
     * Inclui uma nova pergunta no banco de dados.
     * @param pergunta A nova pergunta a ser incluida. Eh uma instancia da classe
     * Pergunta.
     * @throws Exception Caso a pergunta a ser inserida nao seja passada como
     * parametro.
     * @throws SQLException Caso ocorra erro na inser??o da pergunta.
     */
    public static void incluir (Pergunta pergunta) throws Exception
    {
        if (pergunta==null)
            throw new Exception ("pergunta nao fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO Pergunta " +
                  "(IDPERGUNTA,DATAPERGUNTA,DESCRICAO, RESPONDIDA, FREQUENCIA) " +
                  "VALUES " +
                  "(?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, pergunta.getIdPergunta ());
            BDSQLServer.COMANDO.setDate   (2, pergunta.getDataPergunta ());
            BDSQLServer.COMANDO.setString (3, pergunta.getDescricao ());
            BDSQLServer.COMANDO.setString   (4, pergunta.getRespondida () + "");
            BDSQLServer.COMANDO.setInt    (5, pergunta.getFrequencia ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao inserir pergunta");
        }
    }

    /**
     * Exclui uma pergunta ja existente no banco de dados.
     * @param id O id da pergunta a ser excluida.
     * @throws Exception Caso a busca pela pergunta usando o id
     * fornecido nao de resultado
     * @throws SQLException Caso ocorra erro na exclusao da pergunta.
     */
    public static void excluir (int id) throws Exception
    {
        if (!cadastrado (id))
            throw new Exception ("Nao cadastrada");

        try
        {
            String sql;

            sql = "DELETE FROM PERGUNTA " +
                  "WHERE IDPERGUNTA=?";

            BDSQLServer.COMANDO.prepareStatement (sql);
            

            BDSQLServer.COMANDO.setInt (1, id);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir pergunta");
        }
    }

    /**
     * Altera uma pergunta ja existente no banco de dados.
     * @param pergunta A pergunta a ser alterada. Eh uma instancia da classe
     * Pergunta.
     * @throws Exception Caso a pergunta a ser alterada nao seja passada como
     * par?metro.
     * @throws Exception Caso a pergunta a ser alterado nao exista no 
     * banco de dados.
     * @throws SQLException Caso ocorra erro na alteracao da pergunta.
     */
    public static void alterar (Pergunta pergunta) throws Exception
    {
        if (pergunta==null)
            throw new Exception ("Pergunta nao fornecida");

        if (!cadastrado (pergunta.getIdPergunta()))
            throw new Exception ("Nao cadastrada");

        try
        {
            String sql;

            sql = "UPDATE PERGUNTA " +
                  "SET DATAPERGUNTA=?, " +
                  "DESCRICAO=?, " +
                  "RESPONDIDA=?, " +
                  "FREQUENCIA=? " +
                  "WHERE IDPERGUNTA = ?";  

            BDSQLServer.COMANDO.prepareStatement (sql);

			BDSQLServer.COMANDO.setDate   (1, pergunta.getDataPergunta ());
            BDSQLServer.COMANDO.setString (2, pergunta.getDescricao ());
            BDSQLServer.COMANDO.setString   (3, pergunta.getRespondida () + "");
            BDSQLServer.COMANDO.setInt    (4, pergunta.getFrequencia ());
			BDSQLServer.COMANDO.setInt    (5, pergunta.getIdPergunta ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de pergunta");
        }
    }

    /**
     * Retorna uma pergunta existente no banco de dados, buscando
     * pelo seu id.
     * @param idPergunta O id da pergunta a ser buscada e retornada.
     * @return Caso exista, retorna uma inst?ncia da classe Pergunta
     * semelhante a encontrada na pesquisa usando o id passado como 
     * parametro.
     * @throws Exception Caso a busca pela pergunta nao de resultados.
     * @throws SQLException Caso ocorra erros na busca da pergunta.
     */
    public static Pergunta getPergunta (int idPergunta) throws Exception
    {
    	Pergunta pergunta = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PERGUNTA " +
                  "WHERE IDPERGUNTA = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, idPergunta);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrada");

            pergunta = new Pergunta (resultado.getInt   ("IDPERGUNTA"),
            						 resultado.getInt   ("FREQUENCIA"),
            						 resultado.getDate  ("DATAPERGUNTA"),
            						 resultado.getString ("RESPONDIDA").charAt(0),
                               		 resultado.getString("DESCRICAO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar pergunta");
        }

        return pergunta;
    }
    
    /**
     * Retorna uma pergunta existente no banco de dados, buscando
     * por sua descricao.
     * @param descricao A descricao da pergunta a ser buscada e retornada.
     * @return Caso exista, retorna uma inst?ncia da classe Pergunta
     * semelhante a encontrada na pesquisa usando o id passado como 
     * parametro.
     * @throws Exception Caso a busca pela pergunta nao de resultados.
     * @throws SQLException Caso ocorra erros na busca da pergunta.
     */
    public static Pergunta getPergunta (String descricao) throws Exception
    {
    	Pergunta pergunta = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PERGUNTA " +
                  "WHERE DESCRICAO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString(1, descricao);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrada");

            pergunta = new Pergunta (resultado.getInt   ("IDPERGUNTA"),
            						 resultado.getInt   ("FREQUENCIA"),
            						 resultado.getDate  ("DATAPERGUNTA"),
            						 resultado.getString ("RESPONDIDA").charAt(0),
                               		 resultado.getString("DESCRICAO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar pergunta");
        }

        return pergunta;
    }
    
    /**
     * Busca todas as perguntas existentes no banco de dados.
     * @return Uma instancia da classe MeuResultSet com todos
     * os resultados da busca.
     * @throws SQLException Caso ocorra erros na busca das perguntas
     */
    public static MeuResultSet getPerguntas () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PERGUNTA";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar perguntas");
        }

        return resultado;
    }
}