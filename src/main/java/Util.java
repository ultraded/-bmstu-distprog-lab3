final public class Util {
    private static final String AIRPORTS_DELIMITER = "\",", FLIGHTS_DELIMITER = ",";
    private static final int ORIGIN_INDEX = 11, DESTINATION_INDEX = 14, DELAY_INDEX = 18, CANCELLED_INDEX = 19;
    private static int AIRPORTID_INDEX = 0, AIRPORTNAME_INDEX = 1;
    private static final float ZERO = 0.0f;

    public static boolean isCanceledOrDelayedFilter(String[] flight) {
        return ((Float.parseFloat(flight[CANCELLED_INDEX]) == ZERO)
                || (Float.parseFloat(flight[DELAY_INDEX]) > ZERO));
    }



}
