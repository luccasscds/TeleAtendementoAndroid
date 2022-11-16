package Teleatendimento.com.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseHelper {

    public static String DB_PATH = "C:\\Users\\Lucas\\Projetos\\app\\src\\main\\java\\Teleatendimento\\com\\db\\";
    private static final String DB_NAME = "db_teleAtendimento.db";
    private static final int DB_VERSION = 1;
    private static SQLiteDatabase database;

    public static void CreateDatabase(){
        try {
            database = SQLiteDatabase.openOrCreateDatabase(DB_PATH+DB_NAME, null);
            String tableEndereco = "CREATE TABLE ENDERECO (\n" +
                    "    ID         INTEGER       PRIMARY KEY AUTOINCREMENT,\n" +
                    "    LOGRADOURO VARCHAR (256),\n" +
                    "    NUMERO     INTEGER,\n" +
                    "    CEP        INTEGER,\n" +
                    "    BAIRRO     VARCHAR (50),\n" +
                    "    CIDADE     VARCHAR (30),\n" +
                    "    ESTADO     VARCHAR (20) \n" +
                    ")";
            database.execSQL(tableEndereco);
            database.close();

        } catch (SQLException e){
            Log.e("DB_LOG", "CreateDatabase: "+e.getLocalizedMessage());
        }
    }
}
