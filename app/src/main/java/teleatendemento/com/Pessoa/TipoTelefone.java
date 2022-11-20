package teleatendemento.com.Pessoa;

public class TipoTelefone {
    protected int id;
    public String TIPO;

    public TipoTelefone(int id, String TIPO)
    {
        this.id = id;
        this.TIPO = TIPO;
    }
    public TipoTelefone() { }
    public int ID() {
        return this.id;
    }
}
