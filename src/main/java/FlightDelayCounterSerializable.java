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

    public static add
}
