import java.io.Serializable;

public class CounterSerializable implements Serializable {
    private float maxDelay;
    private int totalFlights, delayedFlights, cancelledFlights;

    public CounterSerializable() {
    }

    public CounterSerializable(float maxDelay, int totalFlights, int delayedFlights, int cancelledFlights) {
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


    public static CounterSerializable addValue(CounterSerializable a, float maxArrDelay, boolean isDelayed, boolean isCancelled) {
        return new CounterSerializable(
                Math.max(a.getMaxDelay(), maxArrDelay),
                a.getTotalFlights() + 1,
                isDelayed ? a.getDelayedFlights() + 1 : a.getDelayedFlights(),
                isCancelled ? a.getDelayedFlights() + 1 : a.getDelayedFlights());
    }

    public static CounterSerializable add(CounterSerializable a, CounterSerializable b) {
        return new CounterSerializable(
                Math.max(a.getMaxDelay(), b.getMaxDelay()),
                a.getTotalFlights() + b.getTotalFlights(),
                a.getDelayedFlights() + b.getDelayedFlights(),
                a.getCancelledFlights() + b.getCancelledFlights());
    }

    public static String toOutString(CounterSerializable a) {
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
