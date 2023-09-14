package com.example.cs435_hw09;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapter extends RecyclerView.Adapter{

    BookAdapterListener bookAdapterListener;

    public interface  BookAdapterListener{
        public void click(int position);
    }

    public BookAdapter(BookAdapterListener bookAdapterListener){
        this.bookAdapterListener = bookAdapterListener;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle;
        TextView textViewAuthor;
        ImageView imageViewCover;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageViewCover = itemView.findViewById(R.id.imageViewCover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    bookAdapterListener.click(position);
                }
            });
        }

        public void update(String title, String author, int id){
            textViewTitle.setText(title);
            textViewAuthor.setText(author);
            imageViewCover.setImageResource(id);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book book = Model.books.get(position);
        BookViewHolder bookViewHolder = (BookViewHolder) holder;
        bookViewHolder.update(book.bookName, book.author, book.imageId);
    }

    @Override
    public int getItemCount() {
        return Model.books.size();
    }
}
