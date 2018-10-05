package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.take_note.db;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NotePresenter {

    private NoteDao dao;

    public NotePresenter(NoteDao dao) {
        this.dao = dao;
    }

    public LiveData<List<Note>> getAllNotes() {
        return dao.getAllNotes();
    }

    public void updateNoteById(String heading, String description, String date, int noteId) {
        new NotePresenter.updateNotesTask(dao).execute(heading, description, date, String.valueOf(noteId));
    }

    public void insertNotes(Note... Notes) {
        new NotePresenter.insertNotesTask(dao).execute(Notes);
    }

    public void deleteSingleItemById(Integer... id) {
        new NotePresenter.deleteNoteByIdTask(dao).execute(id);
    }

    public void deleteNotes(Note... Note) {
        new NotePresenter.deleteNotesTask(dao).execute(Note);
    }

    private static class updateNotesTask extends AsyncTask<String, Void, Void> {

        private NoteDao dao;

        updateNotesTask(NoteDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(String... strings) {
            dao.updateNoteById(strings[0], strings[1], strings[2], Integer.valueOf(strings[3]));
            return null;
        }
    }

    private static class insertNotesTask extends AsyncTask<Note, Void, Void> {

        private NoteDao dao;

        insertNotesTask(NoteDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Note... Notes) {
            dao.insertNotes(Notes);
            return null;
        }
    }

    private static class deleteNoteByIdTask extends AsyncTask<Integer, Void, Void> {

        private NoteDao dao;

        deleteNoteByIdTask(NoteDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Integer... integers) {
            for(Integer i : integers) {
                dao.deleteNotesById(i);
            }
            return null;
        }
    }

    private static class deleteNotesTask extends AsyncTask<Note, Void, Void> {

        private NoteDao dao;

        deleteNotesTask(NoteDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Note... Notes) {
            dao.deleteNotes(Notes);
            return null;
        }
    }
}
