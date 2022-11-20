package teleatendemento.com.Pessoa;

import java.math.BigInteger;
import java.util.List;

public class Pessoa extends PessoaDAO {
    public String NOME;
    public BigInteger CPF;
    public Endereco ENDERECO;
    public List<Telefone> TELEFONES;

    public Pessoa(long id, String NOME, String CPF, Endereco ENDERECO, List<Telefone> TELEFONES)
    {
        this.id = id;
        this.NOME = NOME;
        this.setCPF(CPF);
        this.ENDERECO = ENDERECO;
        this.TELEFONES = TELEFONES;
    }
    public Pessoa() {}
    public void ID(long ID) { this.id = ID; }
    public void setCPF(String CPF) {
        this.CPF = new BigInteger(CPF);
    }
}
