package entities;

public enum ShowingStatus {
    COMING_SOON("COMING_SOON"),
    PREVIEW("PREVIEW"),
    NOW_SHOWING("NOW_SHOWING"),
    FINISHED_SHOWING("FINISHED_SHOWING");

    private final String showingStatus;

    private ShowingStatus(){
        this.showingStatus = "";
    }

    private ShowingStatus(String showingStatus){
        this.showingStatus = showingStatus;
    }

    public String toString(){
        return this.showingStatus;
    }

    public boolean equalsTo(String showingStatus){
        return this.showingStatus.equals(showingStatus);
    }
}
