package teleatendemento.com.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import teleatendemento.com.Pessoa.Pessoa;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static Context context = null;
    private static final String DB_NAME = "db_teleAtendimento.db";
    private static final int DB_VERSION = 1;

    private static String EnderecoTable = "ENDERECO";
    private static String TipoTelefoneTable = "TIPO_TELEFONE";
    private static String TelefoneTable = "TELEFONE";
    private static String PessoaTable = "PESSOA";
    private static String PessoaTelefoneTable = "PESSOA_TELEFONE";

    private static String IdField = "ID";
    private static String LogradouroField = "LOGRADOURO";
    private static String NumeroField = "NUMERO";
    private static String CepField = "CEP";
    private static String BairroField = "BAIRRO";
    private static String CidadeField = "CIDADE";
    private static String EstadoField = "ESTADO";
    private static String TipoField = "TIPO";
    private static String DddField = "DDD";
    private static String TipoIdField = "TIPO_ID";
    private static String NomeField = "NOME";
    private static String CpfField = "CPF";
    private static String EnderecoIdField = "ENDERECO_ID";
    private static String TelefoneIdField = "TELEFONES_ID";
    private static String PessoaIdField = "ID_PESSOA";
    private static String TelefonesIdField = "ID_TELEFONE";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String tableEndereco = "CREATE TABLE IF NOT EXISTS "+EnderecoTable+" ( " +
                                    IdField+"           INTEGER PRIMARY KEY AUTOINCREMENT," +
                                    LogradouroField+"   VARCHAR (256), " +
                                    NumeroField+"       INTEGER, " +
                                    CepField+"          INTEGER, " +
                                    BairroField+"       VARCHAR (50), " +
                                    CidadeField+"       VARCHAR (30), " +
                                    EstadoField+"       VARCHAR (20) " +
                                    ")";
            db.execSQL(tableEndereco);

            String tableTipoTelefone = "CREATE TABLE IF NOT EXISTS "+TipoTelefoneTable+" ( " +
                    IdField+"       INTEGER      PRIMARY KEY AUTOINCREMENT, " +
                    TipoField+"     VARCHAR (10) " +
                    ")";
            db.execSQL(tableTipoTelefone);

            String tableTelefone = "CREATE TABLE IF NOT EXISTS "+TelefoneTable+" ( " +
                    IdField+"       INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NumeroField+"   INTEGER, " +
                    DddField+"      INTEGER, " +
                    TipoIdField+"   INTEGER REFERENCES "+TipoTelefoneTable+" ("+IdField+") ON DELETE CASCADE, " +
                    " FOREIGN KEY ( "+TipoIdField+" )" +
                    "    REFERENCES "+TipoTelefoneTable+" ("+IdField+") " +
                    ")";
            db.execSQL(tableTelefone);

            String tablePessoa = "CREATE TABLE IF NOT EXISTS "+PessoaTable+" ( " +
                    IdField+"           INTEGER       PRIMARY KEY AUTOINCREMENT, " +
                    NomeField+"         VARCHAR (256) NOT NULL, " +
                    CpfField+"          BIGINT        NOT NULL UNIQUE, " +
                    EnderecoIdField+"   INTEGER       REFERENCES "+EnderecoTable+" ("+IdField+") ON DELETE CASCADE, " +
                    TelefoneIdField+"   INTEGER       REFERENCES "+PessoaTelefoneTable+" ("+IdField+") ON DELETE CASCADE " +
                    ")";
            db.execSQL(tablePessoa);

            String tablePessoaTelefone = "CREATE TABLE IF NOT EXISTS "+PessoaTelefoneTable+" ( " +
                    IdField+"           INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PessoaIdField+"     INTEGER REFERENCES "+PessoaTable+" ("+IdField+") ON DELETE CASCADE, " +
                    TelefonesIdField+"  INTEGER REFERENCES "+TelefoneTable+" ("+IdField+") ON DELETE CASCADE " +
                    ")";
            db.execSQL(tablePessoaTelefone);

        } catch (Exception e){
            Toast.makeText(
                    this.context, "Não foi possível criado Banco de dados. Error: "+e.getLocalizedMessage(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+EnderecoTable);
        db.execSQL("DROP TABLE IF EXISTS "+TipoTelefoneTable);
        db.execSQL("DROP TABLE IF EXISTS "+TelefoneTable);
        db.execSQL("DROP TABLE IF EXISTS "+PessoaTable);
        db.execSQL("DROP TABLE IF EXISTS "+PessoaTelefoneTable);
    }

    public void InsertData(Pessoa newPerson) {
        SQLiteDatabase db = this.getWritableDatabase();
        long idEndereco, idTipoTelefone, idTelefone, idPessoa;

        try{
            ContentValues enderecoValues = new ContentValues();
            enderecoValues.put(LogradouroField, newPerson.ENDERECO.LOGRADOURO);
            enderecoValues.put(NumeroField, newPerson.ENDERECO.NUMERO);
            enderecoValues.put(CepField, newPerson.ENDERECO.CEP);
            enderecoValues.put(BairroField, newPerson.ENDERECO.BAIRRO);
            enderecoValues.put(CidadeField, newPerson.ENDERECO.CIDADE);
            enderecoValues.put(EstadoField, newPerson.ENDERECO.ESTADO);
            idEndereco = db.insert(EnderecoTable, null, enderecoValues);

            ContentValues tipoTelefoneValues = new ContentValues();
            tipoTelefoneValues.put(TipoField, newPerson.TELEFONES.get(0).TIPO.TIPO);
            idTipoTelefone = db.insert(TipoTelefoneTable, null, tipoTelefoneValues);

            ContentValues telefoneValues = new ContentValues();
            telefoneValues.put(NumeroField, newPerson.TELEFONES.get(0).NUMERO);
            telefoneValues.put(DddField, newPerson.TELEFONES.get(0).DDD);
            telefoneValues.put(TipoIdField, idTipoTelefone);
            idTelefone = db.insert(TelefoneTable, null, telefoneValues);

            ContentValues pessoaValues = new ContentValues();
            pessoaValues.put(NomeField, newPerson.NOME);
            pessoaValues.put(CpfField, String.valueOf(newPerson.CPF));
            pessoaValues.put(EnderecoIdField, idEndereco);
            pessoaValues.put(TelefoneIdField, idTelefone);
            idPessoa = db.insert(PessoaTable, null, pessoaValues);

            ContentValues pessoaTelefoneValues = new ContentValues();
            pessoaTelefoneValues.put(PessoaIdField, idPessoa);
            pessoaTelefoneValues.put(TelefonesIdField, idTelefone);
            db.insert(PessoaTelefoneTable, null, pessoaTelefoneValues);

            if(idPessoa == -1){ throw new SQLiteException(CpfField+" já existe no banco."); }

            Toast.makeText(this.context, "Dados inseridos com sucesso.", Toast.LENGTH_SHORT).show();
        } catch(SQLiteException e) {
            Toast.makeText(
                    this.context, "Não foi possível inserir os dados. Error: "+e.getLocalizedMessage(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    public Cursor GetAll(String table) {
        String query = "SELECT * FROM "+table;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}
