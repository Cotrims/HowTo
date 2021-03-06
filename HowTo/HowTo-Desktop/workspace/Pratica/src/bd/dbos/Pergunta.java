package bd.dbos;
import java.sql.*;

/**
 * Representa uma pergunta existente no banco de dados,
 * com cada um de seus atributos correspondentes a uma
 * coluna de uma pergunta.
 * @author Vin?cius Martins Cotrim.
 * @author Guilherme Jos? S. A.
 * @since 2019.
 */

public class Pergunta implements Cloneable
{
	/**
	 * O id da pergunta no banco de dados.
	 */
    private int idPergunta;
    
    /**
     * A frequencia em que essa pergunta eh visualizada.
     */
	private int	frequencia;
	
	/**
	 * Uma data que mostra quando a pergunta foi feita.
	 */
    private Date dataPergunta;
    
    /**
     * Um char que indica se a pergunta foi ou nao respondida.
     */
    private char respondida;
    
    /**
     * A descricao da pergunta, ou seja, a pergunta em si.
     */
    private String descricao;

    /**
     * Atribui ao this o valor de seu atributo idPergunta.
     * @param idPergunta O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja inapropriado.
     * ou seja, menor ou igual a zero.
     */
    public void setIdPergunta (int idPergunta) throws Exception
    {
        if (idPergunta <= 0)
            throw new Exception ("id invalido");

        this.idPergunta = idPergunta;
    }
    
    /**
     * Atribui ao this o valor de seu atributo frequencia.
     * @param frequencia O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja inapropriado.
     * ou seja menor que zero.
     */
    public void setFrequencia (int frequencia) throws Exception
	{
	    if (frequencia < 0)
	        throw new Exception ("Frequ?ncia invalida");

	    this.frequencia = frequencia;
    }

    /**
     * Atribui ao this o valor de seu atributo data.
     * @param data Uma data que eh valor a ser atribuido.
     * @throws Exception Caso a data passada como parametro seja nula.
     */
    public void setDataPergunta (Date data) throws Exception
    {
        if (data == null)
            throw new Exception ("Data invalida");
		
        this.dataPergunta = data;
    }

    /**
     * Atribui ao this o valor de seu atributo respondida.
     * @param respondida O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja inapropriado.
     * ou seja, vazio.
     */
    public void setRespondida (char respondida) throws Exception
	{
	    if (respondida == ' ')
	        throw new Exception ("Parametro valido");

	    this.respondida = respondida;
    }

    /**
     * Atribui ao this o valor de seu atributo descricao.
     * @param descricao O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja nulo ou
     * vazio.
     */
    public void setDescricao (String descricao) throws Exception
	{
	    if (descricao == null || descricao.equals(""))
	        throw new Exception ("Descricao invalida");

	    this.descricao = descricao;
    }

    /**
     * Retorna o valor do atributo idPergunta.
     * @return O valor que this possui para seu
     * atributo idPergunta.
     */
    public int getIdPergunta ()
    {
        return this.idPergunta;
    }

    /**
     * Retorna o valor do atributo frequencia.
     * @return O valor que this possui para seu
     * atributo frequencia.
     */
    public int getFrequencia ()
    {
        return this.frequencia;
    }

    /**
     * Retorna o valor do atributo data.
     * @return O valor que this possui para seu
     * atributo data.
     */
    public Date getDataPergunta ()
    {
        return this.dataPergunta;
    }

    /**
     * Retorna o valor do atributo respondida.
     * @return O valor que this possui para seu
     * atributo respondida.
     */
    public char getRespondida()
    {
		return this.respondida;
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
     * Construtor de uma instancia da classe Pergunta.
     * @param idPerg O id da pergunta.
     * @param freq A frequencia da pergunta.
     * @param data Uma Sql Date que representa a data da pergunta.
     * @param resp Um char para representar se tem ou nao uma pergunta
     * @param desc A descricao da pergunta ou seja, a pergunta em si
     * @throws Exception Os setters chamados lancam excessoes.
     */
    public Pergunta (int idPerg, int freq, Date data, char resp, String desc) throws Exception
    {
        this.setIdPergunta (idPerg);
        this.setFrequencia (freq);
        this.setDataPergunta(data);
        this.setRespondida (resp);
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

        ret+="Id: "+this.idPergunta+"\n";
        ret+="Frequ?ncia: "+this.frequencia  +"\n";
        ret+="Data: "+this.dataPergunta  +"\n";
		ret+="Respondida?: "+this.respondida  +"\n";
		ret+="Descri??o: "+this.descricao + "";

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

	    if (!(obj instanceof Pergunta))
	        return false;

	    Pergunta perg = (Pergunta)obj;

	    if (this.idPergunta!=perg.idPergunta)
	        return false;

	    if (this.frequencia!=perg.frequencia)
	        return false;

	    if (!(this.dataPergunta.equals(perg.dataPergunta)))
	        return false;
		
		if(this.respondida!=perg.respondida)
			return false;

		if(this.descricao!=perg.descricao)
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

	    ret += 7*ret + new Integer(this.idPergunta).hashCode();
	    ret += 7*ret + new Integer(this.frequencia).hashCode();
	    ret += 7*ret + this.dataPergunta.hashCode();
	    ret += 7*ret + new Character(this.respondida).hashCode();
	    ret += 7*ret + this.descricao.hashCode();

	    if(ret < 0)
	    	ret = -ret;

	    return ret;
	}

	/**
	 * Constroi uma copia de uma instancia de uma pergunta
	 * e de todos valores de seus atributos.
	 * @param modelo Uma pergunta para ser gerada uma copia.
	 * @throws Exception Caso a pergunta passada como parametro seja nula.
	 */
	public Pergunta (Pergunta modelo) throws Exception
	{
		if(modelo == null)
			throw new Exception("Modelo para copia vazio");

	    this.idPergunta = modelo.idPergunta;
		this.frequencia = modelo.frequencia;
		this.dataPergunta = modelo.dataPergunta;
		this.respondida = modelo.respondida;
		this.descricao = modelo.descricao;
	}

	/**
	 * Instancia um clone de this usando o
	 * construtor de copia.
	 * @return Uma copia de this.
	 */
	public Object clone ()
	{
	    Pergunta ret=null;

	    try
	    {
	        ret = new Pergunta (this);
	    }
	    catch (Exception erro)
	    {}

	   	return ret;
    }
}