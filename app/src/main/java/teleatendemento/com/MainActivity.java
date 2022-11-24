package teleatendemento.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        no_data = findViewById(R.id.no_data);

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
            no_data.setVisibility(View.VISIBLE);
        } else {
            itemListViem = new ItemListViem(this, newPeople);
            recyclerView.setAdapter(itemListViem);
            recyclerView.setLayoutManager( new LinearLayoutManager(this));
            no_data.setVisibility(View.INVISIBLE);
        }
    }
}