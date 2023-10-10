package com.example.roomdbtutorial;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerNoteAdapter extends RecyclerView.Adapter<RecyclerNoteAdapter.ViewHolder> {

    Context context;
    ArrayList<Note> noteArrayList;
    public RecyclerNoteAdapter(Context context,ArrayList<Note> noteArrayList) {
        this.context = context;
        this.noteArrayList=noteArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_card,parent,false);
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(noteArrayList.get(position).getTitle());
        holder.desc.setText(noteArrayList.get(position).getContent());

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);

        holder.icon_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int pos = holder.getAdapterPosition();
//
//                if(pos!=RecyclerView.NO_POSITION) {
//                    Note delnote = noteArrayList.get(pos);
//                    databaseHelper.noteDao().delete(delnote);
//
//                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Note")
                        .setMessage("Are you sure?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Note delnote = noteArrayList.get(position);

                                noteArrayList.remove(position);

                                databaseHelper.noteDao().delete(delnote);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc;
        ImageView icon_delete;

        public ViewHolder(Context context,@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.card_title);
            desc = itemView.findViewById(R.id.card_desc);
            icon_delete = itemView.findViewById(R.id.icon_delete);

        }
    }
}
