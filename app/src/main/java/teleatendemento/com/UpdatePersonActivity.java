package teleatendemento.com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class UpdatePersonActivity extends AppCompatActivity {

    EditText tb_nome, tb_cpf, tb_cep, tb_logradouro, tb_numero, tb_bairro, tb_cidade, tb_ddd, tb_numero_telefone;
    Spinner tb_estado, tb_tipo_telefone;
    String id, id_endereco, id_telefone, id_tipo_telefone, nome, cpf, cep, logradouro, numero, bairro, cidade, estado, ddd, numero_telefone, tipo_telefone;
    Button btn_update, btn_delete;
    AddPersonActivity addActivity = new AddPersonActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_person);

        tb_nome = findViewById(R.id.Tb_nome2);
        tb_cpf = findViewById(R.id.Tb_cpf2);
        tb_cep = findViewById(R.id.Tb_cep2);
        tb_logradouro = findViewById(R.id.Tb_logradouro2);
        tb_numero = findViewById(R.id.Tb_numero2);
        tb_bairro = findViewById(R.id.Tb_bairro2);
        tb_cidade = findViewById(R.id.Tb_cidade2);
        tb_estado = findViewById(R.id.Tb_estado2);
        tb_ddd = findViewById(R.id.Tb_ddd2);
        tb_numero_telefone = findViewById(R.id.Tb_numero_telefone2);
        tb_tipo_telefone = findViewById(R.id.Tb_tipo_telefone2);

        addActivity.getOptionsSpinner(this, tb_estado);
        addActivity.getOptionsSpinnerTipo(this, tb_tipo_telefone);
        getAndSetIntentData();

        btn_delete = findViewById(R.id.Btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        btn_update = findViewById(R.id.Btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper database = new DatabaseHelper(UpdatePersonActivity.this);

                nome = tb_nome.getText().toString();
                cpf = tb_cpf.getText().toString();
                cep = tb_cep.getText().toString();
                logradouro = tb_logradouro.getText().toString();
                numero = tb_numero.getText().toString();
                bairro = tb_bairro.getText().toString();
                cidade = tb_cidade.getText().toString();
                estado = tb_estado.getSelectedItem().toString().equals("Escolha seu estado")
                        ? "" : tb_estado.getSelectedItem().toString();
                ddd = tb_ddd.getText().toString();
                numero_telefone = tb_numero_telefone.getText().toString();
                tipo_telefone = tb_tipo_telefone.getSelectedItem().toString().equals("Escolha tipo de telefone")
                        ? "" : tb_tipo_telefone.getSelectedItem().toString();

                if(tb_cpf.equals("") && nome.equals("")) {
                    Toast.makeText(
                            UpdatePersonActivity.this,
                            "Não pode deixa os campos NOME e CPF vazios.",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    ArrayList<Telefone> telefones = new ArrayList<>();

                    telefones.add( new Telefone(
                            Integer.parseInt(id_telefone), Integer.parseInt(numero_telefone),
                            Integer.parseInt(ddd),
                            new TipoTelefone(Integer.parseInt(id_tipo_telefone), tipo_telefone) )
                    );
                    Endereco endereco = new Endereco(
                            Integer.parseInt(id_endereco), logradouro,
                            Integer.parseInt(numero),
                            Integer.parseInt(cep),
                            bairro, cidade, estado
                    );
                    Pessoa newPerson = new Pessoa(
                            Long.parseLong(id),
                            nome, cpf, endereco, telefones
                    );

                    Pessoa.Alterar(database, newPerson);

                    Intent intent = new Intent(UpdatePersonActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getAndSetIntentData() {
        if( getIntent().hasExtra("ID") &&
            getIntent().hasExtra("NAME") &&
            getIntent().hasExtra("CPF")
        ) {
            id = getIntent().getStringExtra("ID");
            nome = getIntent().getStringExtra("NAME");
            cpf = getIntent().getStringExtra("CPF");
            id_endereco = getIntent().getStringExtra("ID_ENDERECO");
            cep = getIntent().getStringExtra("CEP");
            logradouro = getIntent().getStringExtra("LOGRADOURO");
            numero = getIntent().getStringExtra("NUMERO");
            bairro = getIntent().getStringExtra("BAIRRO");
            cidade = getIntent().getStringExtra("CIDADE");
            estado = getIntent().getStringExtra("ESTADO");
            cidade = getIntent().getStringExtra("CIDADE");
            id_telefone = getIntent().getStringExtra("ID_TELEFONE");
            ddd = getIntent().getStringExtra("DDD");
            numero_telefone = getIntent().getStringExtra("NUMERO_TEL");
            id_tipo_telefone = getIntent().getStringExtra("ID_TIPO_TELEFONE");
            tipo_telefone = getIntent().getStringExtra("TIPO");

            tb_nome.setText(nome);
            tb_cpf.setText(cpf);
            tb_cep.setText(cep);
            tb_logradouro.setText(logradouro);
            tb_numero.setText(numero);
            tb_bairro.setText(bairro);
            tb_cidade.setText(cidade);
            for(int i = 0; i < addActivity.states.size(); i++) {
                if(tb_estado.getItemAtPosition(i).toString().equals(estado)) {
                    tb_estado.setSelection(i);
                }
            }
            tb_ddd.setText(ddd);
            tb_numero_telefone.setText(numero_telefone);
            for(int i = 0; i < addActivity.phones.size(); i++) {
                if(tb_tipo_telefone.getItemAtPosition(i).toString().equals(tipo_telefone)) {
                    tb_tipo_telefone.setSelection(i);
                }
            }
        } else {
            Toast.makeText(this, "Sem dados. :(", Toast.LENGTH_SHORT).show();
        }
    }
    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar "+nome+"?");
        builder.setMessage("Você tem certeza em excluir "+nome+"?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper database = new DatabaseHelper(UpdatePersonActivity.this);
                Pessoa person = new Pessoa(Long.parseLong(id), nome, cpf, null, null);
                Pessoa.Excluir(database, person);

                finish();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}