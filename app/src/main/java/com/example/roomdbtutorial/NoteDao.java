package com.example.roomdbtutorial;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note ORDER BY id DESC")
    List<Note> loadAll();

    @Query("SELECT * FROM Note WHERE id IN (:Ids)")
    List<Note> loadByIds(int[] Ids);

    @Query("SELECT * FROM Note WHERE title LIKE :title LIMIT 1")
    Note findByTitle(String title);


    @Insert
    void insert(Note newNote);

    @Delete
    void delete(Note del_note);
}
