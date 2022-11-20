package teleatendemento.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(@NonNull ItemListViem.MyViewHolder holder, int position) {
        holder.Tv_id.setText(String.valueOf(people.get(position).ID()));
        holder.Tv_nome.setText(String.valueOf(people.get(position).NOME));
        holder.Tv_cpf.setText(String.valueOf(people.get(position).CPF));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Tv_id, Tv_nome, Tv_cpf;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Tv_id = itemView.findViewById(R.id.Tv_Id);
            Tv_nome = itemView.findViewById(R.id.Tv_Nome);
            Tv_cpf = itemView.findViewById(R.id.Tv_Cpf);
        }
    }
}
