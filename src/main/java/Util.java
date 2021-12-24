import scala.Int;
import scala.Tuple2;

final public class Util {
    private static final String DELIMITER = ",";
    private static final int ORIGIN_INDEX = 11, DESTINATION_INDEX = 14, DELAY_INDEX = 18, CANCELLED_INDEX = 19;
    private static int AIRPORTID_INDEX = 0, AIRPORTNAME_INDEX = 1;
    private static final float ZERO = 0.0f;

    private static String removeQuotes(String strWithQuotes) { return strWithQuotes.replaceAll("\'", ""); }

    public static boolean isCanceledOrDelayedFilter(String[] flight) {
        return ((Float.parseFloat(flight[CANCELLED_INDEX]) == ZERO)
                || (Float.parseFloat(flight[DELAY_INDEX]) > ZERO));
    }

    public static Tuple2<Integer,String> parseAirport(String airports) {
        airports = removeQuotes(airports);
        String[] table = airports.split(DELIMITER);
        int airportId = Integer.parseInt(table[AIRPORTID_INDEX]);
        String airportName = table[AIRPORTNAME_INDEX];
        return new Tuple2<>(airportId, airportName);
    }

    public static Tuple2<Tuple2<Integer, Integer>,FlightSerializable> parseFlight(String flight) {
        
    }



}
