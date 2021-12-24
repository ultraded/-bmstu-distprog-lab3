import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Int;
import scala.Tuple2;


public class SparkApp {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JavaRDD<String> flights = sparkContext.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airports = sparkContext.textFile("L_AIRPORT_ID.csv");

        JavaPairRDD<Integer, String> airportsRDD = airports.mapToPair(Util::parseAirport);
        JavaPairRDD<Tuple2<Integer, Integer>, FlightSerializable> flightsRDD = flights.mapToPair(Util::parseFlight);

       
}

