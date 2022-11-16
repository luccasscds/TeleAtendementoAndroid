package Teleatendimento.com.Pessoa;

import java.util.List;

public class Pessoa extends PessoaDAO {
    protected long id;
    public String NOME;
    public long CPF;
    public Endereco ENDERECO;
    public List<Telefone> TELEFONES;

    public Pessoa(long id, String NOME, long CPF, Endereco ENDERECO, List<Telefone> TELEFONES)
    {
        this.id = id;
        this.NOME = NOME;
        this.CPF = CPF;
        this.ENDERECO = ENDERECO;
        this.TELEFONES = TELEFONES;
    }
    public long ID() {
        return this.id;
    }
}
