package com.example.gb_2_h_notes.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gb_2_h_notes.R;
import com.example.gb_2_h_notes.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private ArrayList<Note> data = new ArrayList<>();

    public void addData(List<Note> toAdd) {
        data.addAll(toAdd);
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = data.get(position);

        holder.id.setText(note.getStringId());
        holder.title.setText(note.getTitle());
        holder.date.setText(note.getDate());

        Glide.with(holder.image)
                .load(note.getImageUrl())
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView title;
        TextView date;
        ImageView image;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.note_item_id);
            title = itemView.findViewById(R.id.note_item_title);
            date = itemView.findViewById(R.id.note_item_date);
            image = itemView.findViewById(R.id.note_item_image);
        }
    }
}
