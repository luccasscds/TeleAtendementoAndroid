package teleatendemento.com.Pessoa;

import android.database.Cursor;

import java.util.ArrayList;

import teleatendemento.com.db.DatabaseHelper;

/*
 * Essa classe em questão é a responsável por buscar, inserir, deletar e alterar os dados
 * do banco da aplicação
 */
public class PessoaDAO {
    protected long id;
    public long ID() { return this.id; }

    private static DatabaseHelper Database = null;

    // Consultar
    public static ArrayList<Pessoa> Consultar(DatabaseHelper database, String cpf)
    {
        Database = database;
        if(cpf == "all") {
            return GetAllPeople();
        } else {
            return GetPerson(cpf);
        }
    }

    // Inserir
    public static void Inserir(DatabaseHelper database, Pessoa newPerson)
    {
        if(!newPerson.NOME.equals("") && !newPerson.CPF.equals("")) {
            Database = database;
            Cursor cursor = Database.GetAll("PESSOA");
            boolean isCpf = false;

            if(cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    isCpf = newPerson.CPF.toString().equals(cursor.getString(2));
                }
            }
            if(!isCpf) database.InsertData(newPerson);
        }
    }

    // Excluir
    public static void Excluir(DatabaseHelper database, Pessoa person) {
        if(person.ID() > -1) {
            Database = database;
            Database.deleteOneData(person.ID());
        }
    }

    // Alterar
    public static void Alterar(DatabaseHelper database, Pessoa person) {
        if(!person.CPF.equals("") && !person.NOME.isEmpty() && person.NOME.length() > 1) {
            Database = database;
            Database.UpdateData(person);
        }
    }
    protected static ArrayList<Pessoa> GetAllPeople()
    {
        Cursor pessoaTable = Database.GetAll("PESSOA");
        Cursor enderecoTable = Database.GetAll("ENDERECO");
        Cursor pessoaTelefoneTable = Database.GetAll("PESSOA_TELEFONE");
        Cursor telefoneTable = Database.GetAll("TELEFONE");
        Cursor tipoTelefoneTable = Database.GetAll("TIPO_TELEFONE");

        ArrayList<Pessoa> people = new ArrayList<>();
        Endereco endereco;
        ArrayList<Telefone> telefone;
        TipoTelefone tipoTelefone;

        if(pessoaTable.getCount() > 0 && enderecoTable.getCount() > 0 &&
                pessoaTelefoneTable.getCount() > 0
        ){

            while(pessoaTable.moveToNext()) {

                tipoTelefoneTable.moveToNext();
                tipoTelefone = new TipoTelefone(
                        tipoTelefoneTable.getInt(0), tipoTelefoneTable.getString(1)
                );

                pessoaTelefoneTable.moveToNext();

                telefoneTable.moveToNext();
                telefone = new ArrayList<>();
                telefone.add( new Telefone(
                        telefoneTable.getInt(0), telefoneTable.getInt(1),
                        telefoneTable.getInt(2),
                        telefoneTable.getInt(3) == tipoTelefoneTable.getInt(0) ? tipoTelefone : null
                ));
                boolean isTelefone = pessoaTable.getInt(0) == pessoaTelefoneTable.getInt(1) &&
                        pessoaTelefoneTable.getInt(2) == telefoneTable.getInt(0);

                enderecoTable.moveToNext();
                endereco = new Endereco(
                        enderecoTable.getInt(0), enderecoTable.getString(1),
                        enderecoTable.getInt(2), enderecoTable.getInt(3),
                        enderecoTable.getString(4), enderecoTable.getString(5),
                        enderecoTable.getString(6)
                );

                people.add( new Pessoa(
                        pessoaTable.getLong(0), pessoaTable.getString(1),
                        pessoaTable.getString(2),
                        endereco.ID() == pessoaTable.getInt(3) ? endereco : null,
                        isTelefone ? telefone : null
                ));
            }
        }

        return people;
    }
    protected static ArrayList<Pessoa> GetPerson(String cpf)
    {
        ArrayList<Pessoa> person = new ArrayList<>();

        Cursor cursor = Database.GetAll("PESSOA");
        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                if(cursor.getString(2).equals(cpf)) {
                    person.add( new Pessoa(
                            cursor.getLong(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            null,
                            null
                    ));
                }
            }
        }

        return person;
    }
}
