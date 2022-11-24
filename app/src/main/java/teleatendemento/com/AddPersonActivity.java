package teleatendemento.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import teleatendemento.com.Pessoa.Endereco;
import teleatendemento.com.Pessoa.Pessoa;
import teleatendemento.com.Pessoa.Telefone;
import teleatendemento.com.Pessoa.TipoTelefone;
import teleatendemento.com.db.DatabaseHelper;

public class AddPersonActivity extends AppCompatActivity {


    EditText tb_nome, tb_cpf, tb_cep, tb_logradouro, tb_numero, tb_bairro, tb_cidade, tb_ddd, tb_numero_telefone;
    Spinner tb_estado, tb_tipo_telefone;
    Button btn_ok;
    protected static ArrayList<String> states = new ArrayList<>();
    protected static ArrayList<String> phones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        tb_nome = findViewById(R.id.Tb_nome);
        tb_cpf = findViewById(R.id.Tb_cpf);
        tb_cep = findViewById(R.id.Tb_cep);
        tb_logradouro = findViewById(R.id.Tb_logradouro);
        tb_numero = findViewById(R.id.Tb_numero);
        tb_bairro = findViewById(R.id.Tb_bairro);
        tb_cidade = findViewById(R.id.Tb_cidade);
        tb_estado = findViewById(R.id.Tb_estado);
        tb_ddd = findViewById(R.id.Tb_ddd);
        tb_numero_telefone = findViewById(R.id.Tb_numero_telefone);
        tb_tipo_telefone = findViewById(R.id.Tb_tipo_telefone);

        getOptionsSpinner(this, tb_estado);
        getOptionsSpinnerTipo(this, tb_tipo_telefone);

        btn_ok = findViewById(R.id.Btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper database = new DatabaseHelper(AddPersonActivity.this);

                String numero_telefone = tb_numero_telefone.getText().toString().equals("") ? "0" : tb_numero_telefone.getText().toString();
                String ddd = tb_ddd.getText().toString().equals("") ? "0" : tb_ddd.getText().toString();
                String numero = tb_numero.getText().toString().equals("") ? "0" : tb_numero.getText().toString();
                String cep = tb_cep.getText().toString().equals("") ? "0" : tb_cep.getText().toString();
                String cpf = tb_cpf.getText().toString().equals("") ? "0" : tb_cpf.getText().toString();
                String estado = tb_estado.getSelectedItem().toString().equals("Escolha seu estado")
                        ? "" : tb_estado.getSelectedItem().toString();
                String tipo_telefone = tb_tipo_telefone.getSelectedItem().toString().equals("Escolha tipo de telefone")
                        ? "" : tb_tipo_telefone.getSelectedItem().toString();

                if(cpf.equals("0") && tb_nome.getText().toString().equals("")) {
                    Toast.makeText(
                            AddPersonActivity.this,
                            "Não pode deixa os campos NOME e CPF vazios.",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    ArrayList<Telefone> telefones = new ArrayList<>();

                    telefones.add( new Telefone(
                            0, Integer.parseInt(numero_telefone),
                            Integer.parseInt(ddd),
                            new TipoTelefone(0, tipo_telefone ) )
                    );
                    Endereco endereco = new Endereco(
                            0, tb_logradouro.getText().toString(),
                            Integer.parseInt(numero),
                            Integer.parseInt(cep),
                            tb_bairro.getText().toString(),
                            tb_cidade.getText().toString(),
                            estado
                    );
                    Pessoa newPerson = new Pessoa(
                            0,
                            tb_nome.getText().toString(),
                            cpf,
                            endereco,
                            telefones
                    );

                    Pessoa.Inserir(database, newPerson);

                    Intent intent = new Intent(AddPersonActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    protected static void getOptionsSpinner(Context context, Spinner tb_estado) {
        states.clear();
        states.add("Escolha seu estado");
        states.add("AC");
        states.add("AL");
        states.add("AP");
        states.add("AM");
        states.add("BA");
        states.add("CE");
        states.add("ES");
        states.add("GO");
        states.add("MA");
        states.add("MT");
        states.add("MS");
        states.add("MG");
        states.add("PA");
        states.add("PB");
        states.add("PR");
        states.add("PE");
        states.add("PI");
        states.add("RJ");
        states.add("RN");
        states.add("RS");
        states.add("RO");
        states.add("RR");
        states.add("SC");
        states.add("SP");
        states.add("SE");
        states.add("TO");
        states.add("DF");
        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tb_estado.setAdapter(adapter);
    }
    protected static void getOptionsSpinnerTipo(Context context, Spinner tb_tipo_telefone) {
        phones.clear();
        phones.add("Escolha tipo de telefone");
        phones.add("celular");
        phones.add("residencial");
        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, phones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tb_tipo_telefone.setAdapter(adapter);
    }
}