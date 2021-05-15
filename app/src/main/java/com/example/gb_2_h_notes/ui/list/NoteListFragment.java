package com.example.gb_2_h_notes.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gb_2_h_notes.R;
import com.example.gb_2_h_notes.domain.MockNotesRepository;
import com.example.gb_2_h_notes.domain.Note;
import com.example.gb_2_h_notes.ui.NoteDetailFragment;

import java.util.List;

public class NoteListFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";

    private boolean isLandscape;

    private Note currentNote;

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

        isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        }

/*
        if (isLandscape) {
            openNoteDetail(view, currentNote);
        }
*/

        List<Note> notes = new MockNotesRepository().getNotes();

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        notesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        NotesAdapter adapter = new NotesAdapter();

        notesList.setAdapter(adapter);

        adapter.addData(notes);

        adapter.setNotesListItemClickListener(new NotesAdapter.OnNotesListItemClickListener() {
            @Override
            public void onNotesListItemClick(View view, int position) {
                currentNote = notes.get(position);
                openNoteDetail(view, currentNote);
            }
        });

        adapter.notifyDataSetChanged(); // перерисовка списка
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_NOTE, currentNote);
    }

    private void openNoteDetail(View view, Note note) {

        AppCompatActivity activity = (AppCompatActivity) view.getContext();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (isLandscape) {
            transaction.replace(R.id.fragment_ext, NoteDetailFragment.newInstance(note));
        } else {
            transaction.replace(R.id.fragment_container, NoteDetailFragment.newInstance(note));
        }

        transaction.addToBackStack(null).commit();
    }
}