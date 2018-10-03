package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbusters.robinkamboj.senseitall.R;
import com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
        noteHolder.headerText.setText(list.get(position).getHeading());
        noteHolder.descriptionText.setText(list.get(position).getDescription());
        noteHolder.dateText.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header_text)
        TextView headerText;

        @BindView(R.id.description_text)
        TextView descriptionText;

        @BindView(R.id.date_text)
        TextView dateText;

        NoteHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
