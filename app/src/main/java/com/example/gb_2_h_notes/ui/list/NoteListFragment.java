package com.example.gb_2_h_notes.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gb_2_h_notes.R;
import com.example.gb_2_h_notes.domain.MockNotesRepository;
import com.example.gb_2_h_notes.domain.Note;
import com.example.gb_2_h_notes.ui.MainActivity;
import com.example.gb_2_h_notes.ui.NoteDetailFragment;

import java.util.List;

public class NoteListFragment extends Fragment {

    private boolean isLandscape;

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

        List<Note> notes = new MockNotesRepository().getNotes();

        RecyclerView notesList = view.findViewById(R.id.notes_list);

        notesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        NotesAdapter adapter = new NotesAdapter();

        notesList.setAdapter(adapter);

        adapter.addData(notes);

        adapter.setNotesListItemClickListener(new NotesAdapter.OnNotesListItemClickListener() {
            @Override
            public void onNotesListItemClick(View view, int position) {
                openNoteDetail(view, position, notes.get(position));
            }
        });

        adapter.notifyDataSetChanged(); // перерисовка списка
    }

    private void openNoteDetail(View view, int position, Note note) {
//        Toast.makeText(getContext(), note.getTitle() + " (on " + position + " position)", Toast.LENGTH_SHORT).show();

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