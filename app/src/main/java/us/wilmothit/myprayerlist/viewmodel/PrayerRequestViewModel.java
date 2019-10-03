package us.wilmothit.myprayerlist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import us.wilmothit.myprayerlist.PrayerRequestRepository;
import us.wilmothit.myprayerlist.model.PrayerRequest;

public class PrayerRequestViewModel extends AndroidViewModel {

    private PrayerRequestRepository repository;
    private LiveData<List<PrayerRequest>> allPrayerRequests;

    public PrayerRequestViewModel(@NonNull Application application) {
        super(application);
        repository = new PrayerRequestRepository(application);
        allPrayerRequests = repository.getAll();
    }

    public void insert(PrayerRequest prayerRequest) {
        repository.insert(prayerRequest);
    }

    public void update(PrayerRequest prayerRequest) {
        repository.update(prayerRequest);
    }

    public void delete(PrayerRequest prayerRequest) {
        repository.delete(prayerRequest);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<PrayerRequest>> getAll() {
        return allPrayerRequests;
    }

}
