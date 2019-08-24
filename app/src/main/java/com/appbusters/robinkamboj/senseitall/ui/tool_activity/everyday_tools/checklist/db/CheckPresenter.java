package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.checklist.db;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CheckPresenter {

    private CheckDao dao;

    public CheckPresenter(CheckDao dao) { this.dao = dao; }


    public LiveData<List<Check>> getAllChecks() {
        return dao.getAllChecks();
    }

    public void updateCheckById(String check_text, boolean isDone, int id) {
        new CheckPresenter.updateChecksTask(dao).execute(check_text, String.valueOf(isDone), String.valueOf(id));
    }

    public void updateIsDoneById(boolean isDone, int id) {
        new CheckPresenter.updateIsDoneTask(dao).execute(String.valueOf(isDone), String.valueOf(id));
    }

    public void insertChecks(Check... Checks) {
        new CheckPresenter.insertChecksTask(dao).execute(Checks);
    }

    public void deleteSingleItemById(Integer... id) {
        new CheckPresenter.deleteCheckByIdTask(dao).execute(id);
    }

    public void deleteChecks(Check... checks) {
        new CheckPresenter.deleteChecksTask(dao).execute(checks);
    }

    private static class updateChecksTask extends AsyncTask<String, Void, Void> {

        private CheckDao dao;

        updateChecksTask(CheckDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(String... strings) {
            dao.updateCheckById(strings[0], Boolean.valueOf(strings[1]), Integer.valueOf(strings[3]));
            return null;
        }
    }

    private static class updateIsDoneTask extends AsyncTask<String, Void, Void> {

        private CheckDao dao;

        updateIsDoneTask(CheckDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(String... strings) {
            dao.updateIsDoneById(Boolean.valueOf(strings[1]), Integer.valueOf(strings[3]));
            return null;
        }
    }

    private static class insertChecksTask extends AsyncTask<Check, Void, Void> {

        private CheckDao dao;

        insertChecksTask(CheckDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Check... Checks) {
            dao.insertChecks(Checks);
            return null;
        }
    }

    private static class deleteCheckByIdTask extends AsyncTask<Integer, Void, Void> {

        private CheckDao dao;

        deleteCheckByIdTask(CheckDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Integer... integers) {
            for(Integer i : integers) {
                dao.deleteCheckById(i);
            }
            return null;
        }
    }

    private static class deleteChecksTask extends AsyncTask<Check, Void, Void> {

        private CheckDao dao;

        deleteChecksTask(CheckDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Check... Checks) {
            dao.deleteChecks(Checks);
            return null;
        }
    }
}
