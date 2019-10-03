package us.wilmothit.myprayerlist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import us.wilmothit.myprayerlist.model.PrayerRequest;
import us.wilmothit.myprayerlist.model.PrayerRequestDao;

public class PrayerRequestRepository {

    private PrayerRequestDao dao;
    private LiveData<List<PrayerRequest>> allPrayerRequests;

    public PrayerRequestRepository(Application application) {
        MyPrayerListDatabase database = MyPrayerListDatabase.getInstance(application);
        dao = database.prayerRequestDao();
        allPrayerRequests = dao.getAll();
    }

    public void insert(PrayerRequest prayerRequest) {
        new InsertPrayerRequestAsyncTask(dao).execute(prayerRequest);
    }

    public void update(PrayerRequest prayerRequest) {
        new UpdatePrayerRequestAsyncTask(dao).execute(prayerRequest);
    }

    public void delete(PrayerRequest prayerRequest) {
        new DeletePrayerRequestAsyncTask(dao).execute(prayerRequest);
    }

    public void deleteAll() {
        new DeleteAllPrayerRequestAsyncTask(dao).execute();
    }

    public LiveData<List<PrayerRequest>> getAll() {
        return allPrayerRequests;
    }

    //Async tasks

    private static class InsertPrayerRequestAsyncTask extends AsyncTask<PrayerRequest, Void, Void> {
        private PrayerRequestDao dao;

        public InsertPrayerRequestAsyncTask(PrayerRequestDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PrayerRequest... prayerRequests) {
            dao.insert(prayerRequests[0]);
            return null;
        }
    }

    private static class UpdatePrayerRequestAsyncTask extends AsyncTask<PrayerRequest, Void, Void> {
        private PrayerRequestDao dao;

        public UpdatePrayerRequestAsyncTask(PrayerRequestDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PrayerRequest... prayerRequests) {
            dao.update(prayerRequests[0]);
            return null;
        }
    }

    private static class DeletePrayerRequestAsyncTask extends AsyncTask<PrayerRequest, Void, Void> {
        private PrayerRequestDao dao;

        public DeletePrayerRequestAsyncTask(PrayerRequestDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PrayerRequest... prayerRequests) {
            dao.delete(prayerRequests[0]);
            return null;
        }
    }

    private static class DeleteAllPrayerRequestAsyncTask extends AsyncTask<Void, Void, Void> {
        private PrayerRequestDao dao;

        public DeleteAllPrayerRequestAsyncTask(PrayerRequestDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }

}
