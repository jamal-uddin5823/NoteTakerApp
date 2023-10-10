package com.example.roomdbtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNote extends AppCompatActivity {

    EditText etTitle,etDesc;
    Button btnAdd,btnLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDesc);
        btnAdd = findViewById(R.id.btnAdd);
        btnLoad = findViewById(R.id.btnLoad);
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etTitle.getText().toString().isEmpty() || etDesc.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please enter something",Toast.LENGTH_SHORT).show();

                }
                else{
                    databaseHelper.noteDao().insert(
                            new Note(etTitle.getText().toString(),etDesc.getText().toString())
                    );
                    Toast.makeText(getApplicationContext(),"Added new note",Toast.LENGTH_SHORT).show();
                    etTitle.setText("");
                    etDesc.setText("");
                }


            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewNote.this, NoteList.class);
                startActivity(intent);
            }
        });


    }
}