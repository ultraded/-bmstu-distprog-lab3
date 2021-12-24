import java.io.Serializable;

public class FlightSerializable implements Serializable {
    private int originId, destinationId;
    private float delay, cancelled;


    public FlightSerializable() {
    }

    public FlightSerializable(int originId, int destinationId, float delay, float cancelled) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.delay = delay;
        this.cancelled = cancelled;
    }

    public int getOriginId() {
        return originId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public float getDelay() {
        return delay;
    }

    public float getCancelled() {
        return cancelled;
    }
}
