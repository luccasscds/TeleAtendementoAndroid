package Teleatendimento.com.Pessoa;

public class Endereco {
    protected int id;
    public String LOGRADOURO;
    public int NUMERO;
    public int CEP;
    public String BAIRRO;
    public String CIDADE;
    public String ESTADO;

    public Endereco(int id, String LOGRADOURO, int NUMERO, int CEP, String BAIRRO, String CIDADE, String ESTADO)
    {
        this.id = id;
        this.LOGRADOURO = LOGRADOURO;
        this.NUMERO = NUMERO;
        this.CEP = CEP;
        this.BAIRRO = BAIRRO;
        this.CIDADE = CIDADE;
        this.ESTADO = ESTADO;
    }
    public Endereco() { }

    public int ID(){
        return this.id;
    }
}
