package Teleatendimento.com.Pessoa;

public class Telefone {
    protected int id;
    public int NUMERO;
    public int DDD;
    public TipoTelefone TIPO;

    public Telefone(int id, int NUMERO, int DDD, TipoTelefone TIPO)
    {
        this.id = id;
        this.NUMERO = NUMERO;
        this.DDD = DDD;
        this.TIPO = TIPO;
    }
    public Telefone() { }
    public int ID() {
        return this.id;
    }
}
