package Teleatendimento.com.Pessoa;

import Teleatendimento.com.db.DatabaseHelper;

/*
 * Essa classe em questão é a responsável por buscar, inserir, deletar e alterar os dados
 * do banco da aplicação
 */
public class PessoaDAO {
    // Consultar
    public static Pessoa[] Consultar(String cpf)
    {
        if (cpf == "")
        {
            return GetAllPeople();
        }
        else
        {
            return GetAllPeople();
        }
    }

    protected static Pessoa[] GetAllPeople()
    {
        try
        {
            Pessoa[] pessoas = new Pessoa[1];

            for (int i = 0; i < 1; i++)
            {
                pessoas[i] = new Pessoa(
                    0,
                    "lucas",
                    07760,
                    null,
                    null
                );
            }
            return pessoas;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
