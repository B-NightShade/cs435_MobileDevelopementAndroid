package com.example.exam2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IntegerAdapter extends RecyclerView.Adapter{

    public interface IntegerAdapterListener{
        public void clicked(int position);
    }

    IntegerAdapterListener integerAdapterListener;

    public IntegerAdapter(IntegerAdapterListener integerAdapterListener) {
        this.integerAdapterListener = integerAdapterListener;
    }

    public class IntegerHolder extends RecyclerView.ViewHolder{
        TextView textViewInt;
        Button buttonAdd;
        Button buttonSub;
        Button buttonDelete;

        public IntegerHolder(@NonNull View itemView) {
            super(itemView);

            textViewInt = itemView.findViewById(R.id.textViewInteger);
            buttonAdd = itemView.findViewById(R.id.buttonPlus);
            buttonSub = itemView.findViewById(R.id.buttonSub);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Model.numbers.remove(position);
                    MainActivity.recyclerView.getAdapter().notifyDataSetChanged();
                }
            });

            buttonSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    int original = Model.numbers.get(position);
                    original = original - 1;
                    Model.numbers.set(position, original);
                    MainActivity.recyclerView.getAdapter().notifyDataSetChanged();
                }
            });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    int original = Model.numbers.get(position);
                    original = original + 1;
                    Model.numbers.set(position, original);
                    MainActivity.recyclerView.getAdapter().notifyDataSetChanged();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    integerAdapterListener.clicked(position);
                }
            });
        }

        public void update(int integer){
            textViewInt.setText(String.valueOf(integer));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new IntegerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        IntegerHolder integerHolder = (IntegerHolder) holder;
        int integer = Model.numbers.get(position);
        integerHolder.update(integer);
    }

    @Override
    public int getItemCount() {
        return Model.numbers.size();
    }
}
