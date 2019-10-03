package us.wilmothit.myprayerlist.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import us.wilmothit.myprayerlist.R;
import us.wilmothit.myprayerlist.model.PrayerRequest;

public class PrayerRequestAdapter extends RecyclerView.Adapter<PrayerRequestAdapter.PrayerRequestHolder> {

    private List<PrayerRequest> prayerRequests = new ArrayList<>();

    @NonNull
    @Override
    public PrayerRequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View prayerRequestView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prayerrequest_item, parent, false);
        return new PrayerRequestHolder(prayerRequestView);
    }

    @Override
    public void onBindViewHolder(@NonNull PrayerRequestHolder holder, int position) {
        PrayerRequest currentPrayerRequest = prayerRequests.get(position);
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        holder.lastPrayedTxtVw.setText(df.format(currentPrayerRequest.getLastPrayed()));
        holder.summaryTxtVw.setText(currentPrayerRequest.getSummary());
        holder.detailsTxtVw.setText(currentPrayerRequest.getDetails());
    }

    @Override
    public int getItemCount() {
        return prayerRequests.size();
    }

    public void setPrayerRequests(List<PrayerRequest> prayerRequests) {
        this.prayerRequests = prayerRequests;
        notifyDataSetChanged();
    }

    class PrayerRequestHolder extends RecyclerView.ViewHolder {
        private TextView lastPrayedTxtVw;
        private TextView summaryTxtVw;
        private TextView detailsTxtVw;

        public PrayerRequestHolder(@NonNull View itemView) {
            super(itemView);

            this.lastPrayedTxtVw = itemView.findViewById(R.id.lastPrayedTxtVw);
            this.summaryTxtVw = itemView.findViewById(R.id.summaryTxtVw);
            this.detailsTxtVw = itemView.findViewById(R.id.detailsTxtVw);
        }
    }

}
