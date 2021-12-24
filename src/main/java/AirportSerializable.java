import java.io.Serializable;

public class AirportSerializable implements Serializable {
    private int airportId;
    private String airportName;

    public AirportSerializable() {
    }

    public AirportSerializable(int airportId, String airportName) {
        this.airportId = airportId;
        this.airportName = airportName;
    }

    public int getAirportId() {
        return airportId;
    }

    public String getAirportName() {
        return airportName;
    }
}
