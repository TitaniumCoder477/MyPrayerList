package us.wilmothit.myprayerlist;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import us.wilmothit.myprayerlist.model.Converters;
import us.wilmothit.myprayerlist.model.PrayerRequest;
import us.wilmothit.myprayerlist.model.PrayerRequestDao;

@Database(entities = {PrayerRequest.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MyPrayerListDatabase extends RoomDatabase {

    private static MyPrayerListDatabase instance;

    public abstract PrayerRequestDao prayerRequestDao();

    public static synchronized MyPrayerListDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyPrayerListDatabase.class, "myprayerlist_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PrayerRequestDao dao;

        public PopulateDbAsyncTask(MyPrayerListDatabase db) {
            this.dao = db.prayerRequestDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new PrayerRequest("Summary 1", "Details 1", "Requestor 1"));
            dao.insert(new PrayerRequest("Summary 2", "Details 2", "Requestor 2"));
            dao.insert(new PrayerRequest("Summary 3", "Details 3", "Requestor 3"));
            return null;
        }
    }

}
