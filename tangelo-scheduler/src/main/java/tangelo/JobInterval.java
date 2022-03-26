package tangelo;

public enum JobInterval {
    Now(0),
    ThreeSecond(3),//for testing purpose only
    Hour(1*60),
    TwoHours(2*60*60),
    SixHours(6*60*60),
    TwelveHours(12*60*60);

    public final int interval;

    JobInterval(int interval){
        this.interval = interval;
    }

    public int getValue() {
        return interval;
    }
}
