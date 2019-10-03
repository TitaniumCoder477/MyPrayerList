package us.wilmothit.myprayerlist.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PrayerRequestDao {

    @Insert
    void insert(PrayerRequest prayerRequest);

    @Update
    void update(PrayerRequest prayerRequest);

    @Delete
    void delete(PrayerRequest prayerRequest);

    @Query("DELETE FROM prayerrequest_table")
    void deleteAll();

    @Query("SELECT * FROM prayerrequest_table ORDER BY lastPrayed DESC")
    LiveData<List<PrayerRequest>> getAll();

}
