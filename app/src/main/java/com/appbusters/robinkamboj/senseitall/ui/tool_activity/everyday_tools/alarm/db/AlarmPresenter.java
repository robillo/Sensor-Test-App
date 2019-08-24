package com.appbusters.robinkamboj.senseitall.ui.tool_activity.everyday_tools.alarm.db;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AlarmPresenter {

    private AlarmDao dao;

    public AlarmPresenter(AlarmDao dao) {
        this.dao = dao;
    }

    public LiveData<List<Alarm>> getAllAlarms() {
        return dao.getAlarmInstances();
    }

    public LiveData<List<Alarm>> getAllActiveAlarms() {
        return dao.getActiveAlarms();
    }

    public LiveData<List<Alarm>> getAllInactiveAlarms() {
        return dao.getInactiveAlarms();
    }

    public void insertAlarms(Alarm... alarms) {
        new insertAlarmsTask(dao).execute(alarms);
    }

    public void deleteSingleItemById(Integer... id) {
        new deleteAlarmByIdTask(dao).execute(id);
    }

    public void deleteAlarms(Alarm... alarm) {
        new deleteAlarmsTask(dao).execute(alarm);
    }

    private static class insertAlarmsTask extends AsyncTask<Alarm, Void, Void> {

        private AlarmDao dao;

        insertAlarmsTask(AlarmDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Alarm... alarms) {
            dao.insertAlarms(alarms);
            return null;
        }
    }

    private static class deleteAlarmByIdTask extends AsyncTask<Integer, Void, Void> {

        private AlarmDao dao;

        deleteAlarmByIdTask(AlarmDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Integer... integers) {
            dao.deleteAlarmsById(integers);
            return null;
        }
    }

    private static class deleteAlarmsTask extends AsyncTask<Alarm, Void, Void> {

        private AlarmDao dao;

        deleteAlarmsTask(AlarmDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Alarm... alarms) {
            dao.deleteAlarms(alarms);
            return null;
        }
    }

}
