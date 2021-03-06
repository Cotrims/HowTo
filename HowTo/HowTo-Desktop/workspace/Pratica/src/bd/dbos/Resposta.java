package bd.dbos;
import java.sql.*;

/**
 * Representa uma resposta existente no banco de dados,
 * com cada um de seus atributos correspondentes a uma
 * coluna de uma resposta.
 * @author Vin?cius Martins Cotrim.
 * @author Guilherme Jos? S. A.
 * @since 2019
 */
public class Resposta implements Cloneable
{
	/**
	 * O id da resposta no banco de dados.
	 */
    private int idResposta;
    
    /**
     * O id da pergunta a qual a resposta pertence.
     */
    private int idPergunta;
    
    /**
     * O id do ajudante que escreveu a resposta.
     */
	private int idAjudante;
	
	/**
	 * A data de formulacao da resposta.
	 */
    private Date dataResposta;
    
    /**
     * Representa a validade e confiabilidade de uma resposta.
     */
    private char valida;
    
    /**
     * Representa a descricao da resposta, a resposta em si.
     */
    private String descricao;
    
    /**
     * Atribui ao this o valor de seu atributo idResposta.
     * @param idResposta O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja inapropriado,
     * ou seja, menor ou igual a zero.
     */
    public void setIdResposta (int idResposta) throws Exception
    {
        if (idResposta <= 0)
            throw new Exception ("id da resposta invalido");

        this.idResposta = idResposta;
    }

    /**
     * Atribui ao this o valor de seu atributo idPergunta.
     * @param idPergunta O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja inapropriado,
     * ou seja, menor ou igual a zero.
     */
    public void setIdPergunta (int idPergunta) throws Exception
	{
	    if (idPergunta <= 0)
	        throw new Exception ("Id da pergunta invalido");

	    this.idPergunta = idPergunta;
    }

    /**
     * Atribui ao this o valor de seu atributo idAjudante.
     * @param idAjudante O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja inapropriado,
     * ou seja, menor ou igual a zero.
     */
    public void setIdAjudante (int idAjudante) throws Exception
    {
        if (idAjudante <= 0)
            throw new Exception ("Id do Ajudante invalido");

        this.idAjudante = idAjudante;
    }

    /**
     * Atribui ao this o valor de seu atributo data.
     * @param data Um Sql Date, que eh o valor a ser atribuido.
     * @throws Exception Caso a data fornecida seja nulo.
     */
    public void setDataResposta (Date data) throws Exception
    {
        if(data == null)
        	throw new Exception("data ausente");
        this.dataResposta = data;
    }

    /**
     * Atribui ao this o valor de seu atributo valida.
     * @param valida Um char que eh o valor a ser atribuido.
     * @throws Exception Caso o char fornecido seja vazio.
     */
    public void setValida (char valida) throws Exception
	{
	    if (valida == ' ')
	        throw new Exception ("Parametro ausente");

	    this.valida = valida;
    }
    
    /**
     * Atribui ao this o valor de seu atributo descricao.
     * @param descricao Uma String que eh o valor a ser atribuido.
     * @throws Exception Caso a String fornecida seja nula ou caso
     * ela seja invalida(vazia).
     */
    public void setDescricao (String descricao) throws Exception
	{
	    if (descricao == null || descricao.equals(""))
	        throw new Exception ("Descricao invalida ou ausente");

	    this.descricao = descricao;
    }

    /**
     * Retorna o valor do atributo idResposta
     * @return O valor que this possui para seu
     * atributo idResposta.
     */
    public int getIdResposta ()
    {
        return this.idResposta;
    }

    /**
     * Retorna o valor do atributo idPergunta
     * @return O valor que this possui para seu
     * atributo idPergunta.
     */
    public int getIdPergunta ()
    {
        return this.idPergunta;
    }

    /**
     * Retorna o valor do atributo idAjudante.
     * @return O valor que this possui para seu
     * atributo idAjudante.
     */
    public int getIdAjudante ()
    {
        return this.idAjudante;
    }

    /**
     * Retorna o valor do atributo data.
     * @return O valor que this possui para seu
     * atributo data.
     */
    public Date getDataResposta ()
    {
        return this.dataResposta;
    }

    /**
     * Retorna o valor do atributo valida
     * @return O valor que this possui para seu
     * atributo valida.
     */
    public char getValida()
    {
		return this.valida;
	}
    
    /**
     * Retorna o valor do atributo descricao.
     * @return O valor que this possui para seu
     * atributo descricao.
     */
    public String getDescricao ()
    {
        return this.descricao;
    }

    /**
     * Construtor de uma inst?ncia da classe Resposta.
     * @param idResp O id da resposta.
     * @param idPerg O id da pergunta relacionada a resposta.
     * @param idAjud O id do ajudante que escreveu a resposta.
     * @param data A data em que a resposta foi escrita.
     * @param vali A validade dessa resposta.
     * @param desc A resposta por completo. Sua descricao.
     * @throws Exception Os setters chamados lancam excessoes.
     */
    public Resposta (int idResp, int idPerg, int idAjud, Date data, char vali, String desc) throws Exception
    {
        this.setIdResposta (idResp);
        this.setIdPergunta (idPerg);
		this.setIdAjudante (idAjud);
        this.setDataResposta(data);
        this.setValida (vali);
        this.setDescricao (desc);
    }

    /**
     * Gera um String concatenando todos os valores
     * dos atributos presentes em this, para uma
     * representacao visual da classe.
     * @return Uma String com os dados de this.
     */
    public String toString ()
    {
        String ret="";

        ret+="Id: "+this.idResposta+"\n";
        ret+="Id(Pergunta): "+this.idPergunta  +"\n";
        ret+="Data: "+this.dataResposta  +"\n";
		ret+="Valida?: "+this.valida  +"\n";
		ret+="Descri??o: "+this.descricao + "\n";
		ret+="Ajudante: "+this.idAjudante + "";

        return ret;
    }

    /**
     * Compara this a um Object e retorna um boolean
     * que diz a sua relacao de igualdade.
     * @param obj Um objeto a ser verificado a igualdade 
     * com this.
     * @return Um boolean indicando a igualdade entre this e o parametro.
     */
    public boolean equals (Object obj)
	{
	    if (this==obj)
	    	return true;

	    if (obj==null)
	        return false;

	    if (!(obj instanceof Resposta))
	        return false;

	    Resposta resp = (Resposta)obj;

	    if (this.idResposta!=resp.idResposta)
	        return false;

	    if (this.idPergunta!=resp.idPergunta)
	        return false;

	    if (this.idAjudante!=resp.idAjudante)
	        return false;

	    if(!(this.dataResposta.equals(resp.dataResposta)))
	    		return false;

		if(this.valida!=resp.valida)
			return false;

		if(this.descricao!=resp.descricao)
			return false;

	    return true;
   	}

    /**
     * Calcula o HashCode do this chamante do metodo.
     * @return Um inteiro positivo indicando o HashCode resultante.
     */
	public int hashCode ()
	{
		int ret=1;

	    ret += 7*ret + new Integer(this.idResposta).hashCode();
	    ret += 7*ret + new Integer(this.idPergunta).hashCode();
	    ret += 7*ret + new Integer(this.idAjudante).hashCode();
	    ret += 7*ret + this.dataResposta.hashCode();
	    ret += 7*ret + new Character(this.valida).hashCode();
	    ret += 7*ret + this.descricao.hashCode();

	    if(ret < 0)
	    	ret = -ret;

	    return ret;
	}

	/**
	 * Constroi uma copia de uma instancia de uma Resposta
	 * e de todos valores de seus atributos.
	 * @param modelo Uma resposta para ser gerada uma copia.
	 * @throws Exception Caso a resposta passada como parametro seja nula.
	 */
	public Resposta (Resposta modelo) throws Exception
	{
		if(modelo == null)
			throw new Exception("Modelo para copia vazio");

	    this.idResposta = modelo.idResposta;
		this.idPergunta = modelo.idPergunta;
		this.idAjudante = modelo.idAjudante;
		this.dataResposta = modelo.dataResposta;
		this.valida = modelo.valida;
		this.descricao = modelo.descricao;
	}

	/**
	 * Instancia um clone de this usando o
	 * construtor de copia.
	 * @return Uma copia de this.
	 */
	public Object clone ()
	{
	    Resposta ret=null;

	    try
	    {
	        ret = new Resposta (this);
	    }
	    catch (Exception erro)
	    {}

	   	return ret;
    }
}