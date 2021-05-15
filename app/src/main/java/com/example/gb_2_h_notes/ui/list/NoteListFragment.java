package com.example.gb_2_h_notes.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gb_2_h_notes.R;
import com.example.gb_2_h_notes.domain.MockNotesRepository;
import com.example.gb_2_h_notes.domain.Note;

import java.util.List;

public class NoteListFragment extends Fragment {

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Note> notes = new MockNotesRepository().getNotes();

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        notesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        NotesAdapter adapter = new NotesAdapter();

        notesList.setAdapter(adapter);

        adapter.addData(notes);

        adapter.setItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "Click on " + position + " position", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.notifyDataSetChanged(); // перерисовка списка
    }
}