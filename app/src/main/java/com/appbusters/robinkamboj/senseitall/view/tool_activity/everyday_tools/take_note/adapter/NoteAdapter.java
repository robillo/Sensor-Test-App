package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> list;
    private Context context;

    public NoteAdapter(List<Note> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NoteHolder(LayoutInflater.from(context).inflate(R.layout.row_note, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        NoteHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
