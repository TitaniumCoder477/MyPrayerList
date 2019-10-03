package us.wilmothit.myprayerlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class AddPrayerRequestActivity extends AppCompatActivity {

    public static final String EXTRA_LASTPRAYED = "us.wilmothit.myprayerlist.EXTRA_LASTPRAYED";
    public static final String EXTRA_SUMMARY = "us.wilmothit.myprayerlist.EXTRA_SUMMARY";
    public static final String EXTRA_DETAILS = "us.wilmothit.myprayerlist.EXTRA_DETAILS";
    public static final String EXTRA_REQUESTOR = "us.wilmothit.myprayerlist.EXTRA_REQUESTOR";

    private EditText summaryEdtTxt;
    private EditText detailsEdtTxt;
    private EditText requestorEdtTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prayer_request);

        summaryEdtTxt = findViewById(R.id.summaryEdtTxt);
        detailsEdtTxt = findViewById(R.id.detailsEdtTxt);
        requestorEdtTxt = findViewById(R.id.requestorEdtTxt);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Prayer Request");
    }

    private void savePrayerRequest() {
        Date lastPrayed = new Date();
        String summary = summaryEdtTxt.getText().toString();
        String details = detailsEdtTxt.getText().toString();
        String requestor = requestorEdtTxt.getText().toString();

        if(summary.trim().isEmpty() || details.trim().isEmpty() || requestor.trim().isEmpty()) {
            Toast.makeText(this, "Please populate all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_LASTPRAYED, lastPrayed.getTime());
        data.putExtra(EXTRA_SUMMARY, summary);
        data.putExtra(EXTRA_DETAILS, details);
        data.putExtra(EXTRA_REQUESTOR, requestor);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_prayerrequest_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save_prayerrequest:
                savePrayerRequest();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
