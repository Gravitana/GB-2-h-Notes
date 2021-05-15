package com.example.gb_2_h_notes.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gb_2_h_notes.R;
import com.example.gb_2_h_notes.domain.Note;

public class NoteDetailFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";

    private Note note;

    private TextView id, title, body, date;
    private ImageView image;

    public NoteDetailFragment() {
        // Required empty public constructor
    }

    public static NoteDetailFragment newInstance(Note note) {
        NoteDetailFragment fragment = new NoteDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(CURRENT_NOTE, note);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        id = view.findViewById(R.id.note_detail_id);
        title = view.findViewById(R.id.note_detail_title);
        body = view.findViewById(R.id.note_detail_body);
        date = view.findViewById(R.id.note_detail_date);
        image = view.findViewById(R.id.note_detail_image);

        if (getArguments() != null) {
            note = getArguments().getParcelable(CURRENT_NOTE);
        }

        if (note != null) {
            id.setText(note.getStringId());
            title.setText(note.getTitle());
            body.setText(note.getBody());
            date.setText(note.getDate());

            Glide.with(image)
                    .load(note.getImageUrl())
                    .centerCrop()
                    .into(image);
        }
    }

/*
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, note);
        super.onSaveInstanceState(outState);
    }
*/
}