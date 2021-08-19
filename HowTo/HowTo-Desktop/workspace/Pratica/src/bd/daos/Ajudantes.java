package bd.daos;

import java.sql.*;

import bd.*;
import bd.dbos.*;
import bd.core.MeuResultSet; 
/**
 * A classe Ajudantes representa a camada
 * lógica de acesso a tabela ajudante existente 
 * no banco de dados. Nela existem métodos como 
 * inserção e remoção de novos ajudantes.
 * @author Vinícius Martins Cotrim
 * @author Guilherme José S. A.
 * @since 2019
 */
public class Ajudantes
{
	/**
	 * Verifica a existência de um ajudante no banco de dados,
	 * passando seu id como parâmero.
	 * @param id O id do ajudante para verificar sua existencia no banco.
	 * @return Um valor boolean. Verdadeiro caso exista, Falso caso não esteja cadastrado.
	 * @throws SQLException Caso ocorra algum erro na busca pelo ajudante.
	 */
    public static boolean cadastrado (int id) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM AJUDANTE " +
                  "WHERE IDAJUDANTE = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
        }

        return retorno;
    }
    
    /**
     * Verifica a existência de um ajudante no banco de dados,
	 * passando seu email como parâmero.
     * @param email O email do ajudante a ser procurado no bando.
     * @return Verdadeiro caso exista, Falso caso não esteja cadastrado.
     * @throws SQLException Caso ocorra algum erro na busca pelo ajudante.
     */
    public static boolean cadastrado (String email) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM AJUDANTE " +
                  "WHERE EMAIL = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, email);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar ajudante");
        }

        return retorno;
    }

    /**
     * Inclui um novo ajudante no banco de dados.
     * @param ajudante O novo ajudante a ser incluido. Eh uma instancia da classe
     * Ajudante.
     * @throws Exception Caso o ajudante a ser inserido e passado como parametro
     * seja nulo.
     * @throws SQLException Caso ocorra erro na inserção do ajudante.
     */
    public static void incluir (Ajudante ajudante) throws Exception
    {
        if (ajudante==null)
            throw new Exception ("Ajudante nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO AJUDANTE " +
                  "(NOME,EMAIL,SENHA) " +
                  "VALUES " +
                  "(?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, ajudante.getNome ());
            BDSQLServer.COMANDO.setString  (2, ajudante.getEmail ());
            BDSQLServer.COMANDO.setString  (3, ajudante.getSenha ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir ajudante");
        }
    }

    /**
     * Exclui um ajudante ja existente no banco de dados.
     * @param id O id do ajudante a ser excluido.
     * @throws Exception Caso a busca pelo ajudante usando o id
     * fornecido nao de resultado
     * @throws SQLException Caso ocorra erro na exclusao do ajudante
     */
    public static void excluir (int id) throws Exception
    {
        if (!cadastrado (id))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM AJUDANTE " +
                  "WHERE IDAJUDANTE=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir ajudante");
        }
    }

    /**
     * Altera um ajudante ja existente no banco de dados.
     * @param ajudante O ajudante a ser alterado. Eh uma instancia da classe
     * Ajudante.
     * @throws Exception Caso o ajudante a ser alterado nao seja passado como
     * parâmetro.
     * @throws Exception Caso o ajudante a ser alterado nao exista no 
     * banco de dados.
     * @throws SQLException Caso ocorra erro na alteracao do ajudante.
     */
    public static void alterar (Ajudante ajudante) throws Exception
    {
        if (ajudante==null)
            throw new Exception ("Ajudante nao fornecido");

        if (!cadastrado (ajudante.getIdAjudante()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE AJUDANTE " +
                  "SET NOME=?, " +
                  "EMAIL=?, " +
                  "SENHA=? " +
                  "WHERE IDAJUDANTE = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, ajudante.getNome ());
            BDSQLServer.COMANDO.setString (2, ajudante.getEmail ());
            BDSQLServer.COMANDO.setString (3, ajudante.getSenha ());
            BDSQLServer.COMANDO.setInt   (4, ajudante.getIdAjudante());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
        	erro.printStackTrace();
            throw new Exception ("Erro ao atualizar dados de ajudante");
        }
    }

    /**
     * Retorna um ajudante existente no banco de dados, buscando
     * pelo seu id.
     * @param id O id do ajudante a ser buscado e retornado.
     * @return Caso exista, retorna uma instância da classe Ajudante
     * semelhante a encontrada na pesquisa usando o id passado como 
     * parametro.
     * @throws Exception Caso a busca pelo ajudante nao de resultados.
     * @throws SQLException Caso ocorra erros na busca do ajudante.
     */
    public static Ajudante getAjudante (int id) throws Exception
    {
    	Ajudante ajudante = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM AJUDANTE " +
                  "WHERE IDAJUDANTE = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            ajudante = new Ajudante (resultado.getInt    ("IDAJUDANTE"),
                               		 resultado.getString ("NOME"),
                               		 resultado.getString ("EMAIL"),
                               		 resultado.getString ("SENHA"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar ajudante");
        }

        return ajudante;
    }
    
    /**
     * Retorna um ajudante existente no banco de dados, buscando
     * pelo seu email.
     * @param email O email do ajudante a ser buscado e retornado.
     * @return Caso exista, retorna uma instância da classe Ajudante
     * semelhante a encontrada na pesquisa usando o email passado como 
     * parametro.
     * @throws Exception Caso a busca pelo ajudante nao de resultados.
     * @throws SQLException Caso ocorra erros na busca do ajudante.
     */
    public static Ajudante getAjudante (String email) throws Exception
    {
    	Ajudante ajudante = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM AJUDANTE " +
                  "WHERE EMAIL = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, email);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            ajudante = new Ajudante (resultado.getInt    ("IDAJUDANTE"),
                               		 resultado.getString ("NOME"),
                               		 resultado.getString ("EMAIL"),
                               		 resultado.getString ("SENHA"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar ajudante");
        }

        return ajudante;
    }
    
    /**
     * Busca todos os ajudantes existentes no banco de dados.
     * @return Uma instância da classe MeuResultSet com todos
     * os resultados da busca.
     * @throws SQLException Caso ocorra erros na busca dos ajudantes
     */
    public static MeuResultSet getAjudantes () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM AJUDANTE";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar ajudantes");
        }

        return resultado;
    }
}