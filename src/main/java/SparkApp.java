import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Int;
import scala.Tuple2;

import java.util.Map;


public class SparkApp {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JavaRDD<String> flights = sparkContext.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airports = sparkContext.textFile("L_AIRPORT_ID.csv");

        JavaPairRDD<Integer, String> airportsRDD = airports.mapToPair(Util::parseAirport);
        JavaPairRDD<Tuple2<Integer, Integer>, FlightSerializable> flightsRDD = flights.mapToPair(Util::parseFlight);

        JavaPairRDD<Tuple2<Integer, Integer>, CounterSerializable> counters =
                flightsRDD.combineByKey(
                        f -> new CounterSerializable(
                                f.getDelay(),
                                1,
                                f.getDelay() > 0.0f ? 1 : 0,
                                f.getCancelled() == 0.0f ? 0 : 1),
                        (counter, p) -> CounterSerializable.addValue(
                                counter,
                                p.getDelay(),
                                p.getDelay() != 0.0f,
                                p.getCancelled() != 0.0f),
                        CounterSerializable::add);
        JavaPairRDD<Tuple2<Integer, Integer>, String> counterStr = counters.mapToPair(
                value -> {
                    value._2();
                    return new Tuple2<>(value._1(), CounterSerializable.toOutString(value._2()));
                });
        final Broadcast<Map<Integer, String>> broadcast = sparkContext.broadcast(airportsRDD.collectAsMap());

        JavaRDD<String> out = counterStr.map(value -> {
            Map<Integer, String> airportNames = broadcast.value();
            String originName = airportNames.get(value._1()._1()),
                    destinationName = airportNames.get(value._1()._2());
            return originName + " -> " + destinationName + "\n" + value._2();
        });

    }
}

