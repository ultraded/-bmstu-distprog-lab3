import java.io.Serializable;

public class FlightDelayCounterSerializable implements Serializable {
    private float maxDelay;
    private int totalFlights, delayedFlights, cancelledFlights;

    public FlightDelayCounterSerializable() {
    }

    public FlightDelayCounterSerializable(float maxDelay, int totalFlights, int delayedFlights, int cancelledFlights) {
        this.maxDelay = maxDelay;
        this.totalFlights = totalFlights;
        this.delayedFlights = delayedFlights;
        this.cancelledFlights = cancelledFlights;


    }

    public float getMaxDelay() {
        return maxDelay;
    }

    public int getTotalFlights() {
        return totalFlights;
    }

    public int getDelayedFlights() {
        return delayedFlights;
    }

    public int getCancelledFlights() {
        return cancelledFlights;
    }

    public static FlightDelayCounterSerializable addValue(FlightDelayCounterSerializable a, float maxArrDelay, boolean isDelayed, boolean isCancelled) {
        return new FlightDelayCounterSerializable(a.getTotalFlights() + 1,
                isDelayed ? a.getDelayedFlights() + 1 : a.getDelayedFlights(),
                Math.max(a.getMaxDelay(), maxArrDelay),
                isCancelled ? a.getDelayedFlights() + 1 : a.getDelayedFlights());
    }

    public static FlightDelayCounterSerializable add(FlightDelayCounterSerializable a, FlightDelayCounterSerializable b) {
        return new FlightDelayCounterSerializable(a.getTotalFlights() + b.getTotalFlights(),
                a.getDelayedFlights() + b.getDelayedFlights(),
                Math.max(a.getMaxDelay(), b.getMaxDelay()),
                a.getDelayedFlights() + b.getDelayedFlights());
    }

    public static String toOutString(FlightDelayCounterSerializable a) {
        float percentOfDelays = (float) a.getDelayedFlights() / a.getTotalFlights() * 100;
        float percentOfCancelled = (float) a.getDelayedFlights() / a.getTotalFlights() * 100;
        return "INFO : { MaxDelay: " + a.getMaxDelay() +
                "; Flights : "  + a.getTotalFlights() +
                "; Delays : " + a.getDelayedFlights() +
                "; Cancelled : " + a.getDelayedFlights() +
                "}, % of Delays : " + percentOfDelays +
                "%, % of Cancelled : " + percentOfCancelled +
                "%.";
    }
}
