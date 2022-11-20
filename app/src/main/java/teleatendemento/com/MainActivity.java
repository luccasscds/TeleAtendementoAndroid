package teleatendemento.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import teleatendemento.com.Pessoa.Pessoa;
import teleatendemento.com.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper database;

    RecyclerView recyclerView;
    FloatingActionButton btn_add;
    ItemListViem itemListViem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DatabaseHelper(this);

        /*ArrayList<Telefone> telefones = new ArrayList<>();
        telefones.add(
                new Telefone( 0, 988198568, 86, new TipoTelefone(0, "celular") )
        );
        Pessoa newPerson = new Pessoa(0, "Lucas Silva", "27760933398", new Endereco(
                0, "rua aminthas floriano", 751, 64007390, "Água mineral", "Teresina", "PI"
        ), telefones);
        Pessoa.Inserir(database, newPerson);*/

        recyclerView = findViewById(R.id.recyclerView);

        btn_add = findViewById(R.id.Btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
                startActivity(intent);
            }
        });

        GetOptionsRecyclerView();
    }

    public void GetOptionsRecyclerView(){
        ArrayList<Pessoa> newPeople = Pessoa.Consultar(database, "all");

        if(newPeople.size() == 0) {
            Toast.makeText(this, "Sem dados", Toast.LENGTH_SHORT).show();
        } else {
            itemListViem = new ItemListViem(this, newPeople);
            recyclerView.setAdapter(itemListViem);
            recyclerView.setLayoutManager( new LinearLayoutManager(this));
        }
    }
}