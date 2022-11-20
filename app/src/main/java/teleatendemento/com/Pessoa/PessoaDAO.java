package teleatendemento.com.Pessoa;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

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

    protected static ArrayList<Pessoa> GetAllPeople()
    {
        Cursor cursor = Database.GetAll("PESSOA");
        ArrayList<Pessoa> people = new ArrayList<>();

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                people.add( new Pessoa(
                        cursor.getLong(0), cursor.getString(1), cursor.getString(2), null, null
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
