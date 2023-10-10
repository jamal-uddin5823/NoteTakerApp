package com.example.roomdbtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NoteList extends AppCompatActivity {
    ArrayList<Note> noteArrayList = new ArrayList<>();
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);

        RecyclerView recyclerView = findViewById(R.id.rvNoteList);
        fab = findViewById(R.id.fabAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteList.this, NewNote.class);
                startActivity(intent);

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Note> arrayList = (ArrayList<Note>) databaseHelper.noteDao().loadAll();

        for(Note n: arrayList) {
            Log.d("Data","Title: "+n.getTitle()+" desc: "+n.getContent());
        }

        RecyclerNoteAdapter adapter = new RecyclerNoteAdapter(this,arrayList);

        recyclerView.setAdapter(adapter);
    }
}