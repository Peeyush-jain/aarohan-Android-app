package iitropar.aarohan;

public class Event {
    private String teamA;
    private String teamB;
    private String time;
    private String venue ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description ;
    private int type ;
    private int day  ; // which day
    private int pk ;

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public Event(){

    }

    public Event(String teamA, String teamB, String time, String venue, int type, int day) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.time = time;
        this.venue = venue;
        this.type = type;
        this.day = day;

    }
    public Event(String teamA , String teamB , String time , String venue , int type , int day , String description){
        this.teamA = teamA;
        this.teamB = teamB;
        this.time = time;
        this.venue = venue;
        this.type = type;
        this.day = day;
        this.description = description ;
    }
}
