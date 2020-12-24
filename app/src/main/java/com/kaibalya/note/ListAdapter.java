package com.kaibalya.note;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    List<String> listname;
    private Context context;
    BtnListener mbtnListener;

    public ListAdapter(List<String> list, Context context, BtnListener mbtnListener){
        this.listname = list;
        this.context = context;
        this.mbtnListener = mbtnListener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view, mbtnListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        String list = listname.get(position);
        holder.name.setText(list);

    }

    @Override
    public int getItemCount() {
        return listname.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        Button doneItem, editItem;
        BtnListener mbtnListener;

        public ViewHolder(@NonNull View itemView, BtnListener mbtnListener) {
            super(itemView);
            this.mbtnListener = mbtnListener;
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.textView1);
            doneItem = itemView.findViewById(R.id.done);
            editItem = itemView.findViewById(R.id.edit);
            doneItem.setOnClickListener((v) -> {
                if(mbtnListener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        mbtnListener.delete(position);
                    }
                }
            });
            editItem.setOnClickListener((v) -> {
                if(mbtnListener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        mbtnListener.edit(position);
                    }
                }
            });
        }
        @Override
        public void onClick(View v) {
            Button doneItem;
            Toast.makeText(context, name.getText().toString(), Toast.LENGTH_SHORT).show();

        }
    }
    public interface BtnListener{
        void delete(int position);
        void edit(int position);
    }
}
