package us.wilmothit.myprayerlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

import us.wilmothit.myprayerlist.model.PrayerRequest;
import us.wilmothit.myprayerlist.view.PrayerRequestAdapter;
import us.wilmothit.myprayerlist.viewmodel.PrayerRequestViewModel;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_PRAYERREQUEST = 1;

    private PrayerRequestViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addPrayerRequestFab = findViewById(R.id.addPrayerRequestFab);
        addPrayerRequestFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPrayerRequestActivity.class);
                startActivityForResult(intent, ADD_PRAYERREQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PrayerRequestAdapter adapter = new PrayerRequestAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(PrayerRequestViewModel.class);
        viewModel.getAll().observe(this, new Observer<List<PrayerRequest>>() {
            @Override
            public void onChanged(List<PrayerRequest> prayerRequests) {
                adapter.setPrayerRequests(prayerRequests);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_PRAYERREQUEST && resultCode == RESULT_OK) {
            Date lastPrayed = new Date(data.getLongExtra(AddPrayerRequestActivity.EXTRA_LASTPRAYED, 0));
            String summary = data.getStringExtra(AddPrayerRequestActivity.EXTRA_SUMMARY);
            String details = data.getStringExtra(AddPrayerRequestActivity.EXTRA_DETAILS);
            String requestor = data.getStringExtra(AddPrayerRequestActivity.EXTRA_REQUESTOR);

            PrayerRequest prayerRequest = new PrayerRequest(summary,details,requestor);
            viewModel.insert(prayerRequest);

            Toast.makeText(this, "Prayer request saved.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Prayer request not saved.", Toast.LENGTH_SHORT).show();
        }
    }

}
