package teleatendemento.com;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import teleatendemento.com.Pessoa.Pessoa;

public class ItemListViem extends RecyclerView.Adapter<ItemListViem.MyViewHolder> {

    private Context context;
    private ArrayList<Pessoa> people;

    ItemListViem(Context context, ArrayList people){
        this.context = context;
        this.people = people;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListViem.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.Tv_id.setText(String.valueOf(people.get(position).ID()));
        holder.Tv_nome.setText(String.valueOf(people.get(position).NOME));
        holder.Tv_cpf.setText(String.valueOf(people.get(position).CPF));
        holder.itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("DB_LOG", "log: "+people.get(position).ENDERECO.CEP);
                Intent intent = new Intent(context, UpdatePersonActivity.class);
                intent.putExtra("ID", String.valueOf(people.get(position).ID()));
                intent.putExtra("NAME", people.get(position).NOME);
                intent.putExtra("CPF", String.valueOf(people.get(position).CPF));
                intent.putExtra("ID_ENDERECO", String.valueOf(people.get(position).ENDERECO.ID()));
                intent.putExtra("CEP", String.valueOf(people.get(position).ENDERECO.CEP));
                intent.putExtra("LOGRADOURO", String.valueOf(people.get(position).ENDERECO.LOGRADOURO));
                intent.putExtra("NUMERO", String.valueOf(people.get(position).ENDERECO.NUMERO));
                intent.putExtra("BAIRRO", String.valueOf(people.get(position).ENDERECO.BAIRRO));
                intent.putExtra("ESTADO", String.valueOf(people.get(position).ENDERECO.ESTADO));
                intent.putExtra("CIDADE", String.valueOf(people.get(position).ENDERECO.CIDADE));
                intent.putExtra("ID_TELEFONE", String.valueOf(people.get(position).TELEFONES.get(0).ID()));
                intent.putExtra("DDD", String.valueOf(people.get(position).TELEFONES.get(0).DDD));
                intent.putExtra("NUMERO_TEL", String.valueOf(people.get(position).TELEFONES.get(0).NUMERO));
                intent.putExtra("ID_TIPO_TELEFONE", String.valueOf(people.get(position).TELEFONES.get(0).TIPO.ID()));
                intent.putExtra("TIPO", String.valueOf(people.get(position).TELEFONES.get(0).TIPO.TIPO));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Tv_id, Tv_nome, Tv_cpf;
        LinearLayout itemList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Tv_id = itemView.findViewById(R.id.Tv_Id);
            Tv_nome = itemView.findViewById(R.id.Tv_Nome);
            Tv_cpf = itemView.findViewById(R.id.Tv_Cpf);
            itemList = itemView.findViewById(R.id.itemList);
        }
    }
}
