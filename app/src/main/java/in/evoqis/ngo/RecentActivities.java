package in.evoqis.ngo;


public class RecentActivities {

    String date;
    String time;
    String eventName;


    String address;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RecentActivities(String date, String time, String eventName, String address) {
        this.date = date;
        this.time = time;
        this.eventName = eventName;
        this.address = address;
    }
}
