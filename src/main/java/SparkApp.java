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

        JavaPairRDD<Tuple2<Integer, Integer>, FlightSerCount> flightSerCounts =
                dataOfAirportDelays
                        .combineByKey(p -> new FlightSerCount(1,
                                        p.getArrDelay() > ZERO ? 1 : 0,
                                        p.getArrDelay(),
                                        p.getCancelled() == ZERO ? 0 : 1),
                                (flightSerCount, p) -> FlightSerCount.addValue(flightSerCount,
                                        p.getArrDelay(),
                                        p.getArrDelay() != ZERO,
                                        p.getCancelled() != ZERO),
                                FlightSerCount::add);
     }
    JavaPairRDD<Tuple2<Integer, Integer>, String> flightSerCountStrings = flightSerCounts
            .mapToPair(value -> {
                value._2();
                return new Tuple2<>(value._1(), FlightSerCount.toOutString(value._2()));
            });
    final Broadcast<Map<Integer, String>> broadcast = sc.broadcast(dataOfAiportNames.collectAsMap());

    JavaRDD<String> out = flightSerCountStrings.map(value -> {
        Map<Integer, String> airportNames = broadcast.value();

        String aiportNameOfStart = airportNames.get(value._1()._1());
        String aiportNameOfFinish = airportNames.get(value._1()._2());

        return aiportNameOfStart + " -> " + aiportNameOfFinish + "\n" + value._2();
    });

        out.saveAsTextFile("hdfs://localhost:9000/user/ultraded/output");
}

