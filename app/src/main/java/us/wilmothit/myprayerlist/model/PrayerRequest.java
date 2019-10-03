package us.wilmothit.myprayerlist.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "prayerrequest_table")
public class PrayerRequest {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String summary;
    private String details;
    private String requestor;
    private Date lastPrayed;

    public PrayerRequest(String summary, String details, String requestor) {
        this.summary = summary;
        this.details = details;
        this.requestor = requestor;
        this.lastPrayed = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDetails() {
        return details;
    }

    public String getRequestor() {
        return requestor;
    }

    public Date getLastPrayed() {
        return lastPrayed;
    }

    public void setLastPrayed(Date lastPrayed) {
        this.lastPrayed = lastPrayed;
    }
}
