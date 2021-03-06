package bd.dbos;


/**
 * Representa um ajudante existente no banco de dados,
 * com cada um de seus atributos correspondentes a uma
 * coluna de um ajudante.
 * @author Vin?cius Martins Cotrim.
 * @author Guilherme Jos? S. A.
 * @since 2019
 */
public class Ajudante implements Cloneable
{
	/**
	 * O id do ajudante no banco de dados.
	 */
    private int    idAjudante;
    
    /**
     * O nome do ajudante.
     */
    private String nome;
    
    /**
     * O email do ajudante.
     */
    private String email;
    
    /**
     * A senha do ajudante
     */
	private String senha;

	/**
     * Atribui ao this o valor de seu atributo idAjudante.
     * @param idAjudante O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja inapropriado.
     * ou seja, menor ou igual a zero.
     */
    public void setIdAjudante (int idAjudante) throws Exception
    {
        if (idAjudante <= 0)
            throw new Exception ("Codigo invalido");

        this.idAjudante = idAjudante;
    }

    /**
     * Atribui ao this o valor de seu atributo nome.
     * @param nome O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja nulo ou 
     * seja ivalido(vazio).
     */
    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }

    /**
     * Atribui ao this o valor de seu atributo email.
     * @param email O valor a ser atribuido.
     * @throws Exception Caso o valor fornecido seja nulo ou
     * seja ivalido(vazio).
     */
    public void setEmail (String email) throws Exception
    {
        if (email==null || email.equals(""))
            throw new Exception ("Email n?o fornecido");

        this.email = email;
    }

    /**
     * Atribui ao this o valor de seu atributo senha.
     * @param senha O valor a ser atribuido.
     * @throws Exception Caso o valor nao seja fornecido ou 
     * seja ivalido(vazio).
     */
    public void setSenha (String senha) throws Exception
    {
        if (senha==null || senha.equals(""))
            throw new Exception ("Senha n?o fornecida");

        this.senha = senha;
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
     * Retorna o valor do atributo nome.
     * @return O valor que this possui para seu
     * atributo nome.
     */
    public String getNome ()
    {
        return this.nome;
    }

    /**
     * Retorna o valor do atributo email.
     * @return O valor que this possui para seu
     * atributo email.
     */
    public String getEmail ()
    {
        return this.email;
    }

    /**
     * Retorna o valor do atributo senha.
     * @return O valor que this possui para seu
     * atributo senha.
     */
    public String getSenha ()
    {
        return this.senha;
    }

    /**
     * Construtor de uma instancia da classe Ajudante
     * @param id O id do ajudante.
     * @param nome O nome do ajudante.
     * @param email O email do ajudante.
     * @param senha A senha do ajuadante.
     * @throws Exception Os setters chamados lancam excessoes.
     */
    public Ajudante (int id, String nome, String email, String senha) throws Exception
    {
        this.setIdAjudante (id);
        this.setNome   (nome);
        this.setEmail   (email);
        this.setSenha   (senha);
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

        ret+="Id: "+this.idAjudante+"\n";
        ret+="Nome: "+this.nome  +"\n";
        ret+="Email: "+this.email  +"\n";
		ret+="Senha: "+this.senha + "";

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

        if (!(obj instanceof Ajudante))
            return false;

        Ajudante ajud = (Ajudante)obj;

        if (this.idAjudante!=ajud.idAjudante)
            return false;

        if (this.nome.equals(ajud.nome))
            return false;

        if (this.email!=ajud.email)
            return false;

        if (this.senha!=ajud.senha)
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

        ret += 7*ret + new Integer(this.idAjudante).hashCode();
        ret += 7*ret + this.nome.hashCode();
        ret += 7*ret + this.email.hashCode();
        ret += 7*ret + this.senha.hashCode();

        if(ret < 0)
        	ret = -ret;

        return ret;
    }

    /**
	 * Constroi uma copia de uma instancia de um Ajudante
	 * e de todos valores de seus atributos.
	 * @param modelo Um Ajudante para ser gerada uma copia.
	 * @throws Exception Caso o ajudante passado como parametro seja nulo.
	 */
    public Ajudante (Ajudante modelo) throws Exception
    {
		if(modelo == null)
			throw new Exception("Modelo para copia vazio");

        this.idAjudante = modelo.idAjudante;
        this.nome = modelo.nome;
        this.email = modelo.email;
        this.senha = modelo.senha;
    }

    /**
	 * Instancia um clone de this usando o
	 * construtor de copia.
	 * @return Uma copia de this.
	 */
    public Object clone ()
    {
        Ajudante ret=null;

        try
        {
            ret = new Ajudante (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}