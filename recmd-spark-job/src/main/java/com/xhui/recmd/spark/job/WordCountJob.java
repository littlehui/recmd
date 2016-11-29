package com.xhui.recmd.spark.job;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by littlehui on 2016/9/1.
 */
public class WordCountJob {

    private final static String VISIT_PRIFIX = "/commodity/visual/detail.html?commodityCode=";

    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.err.println("Usage: WordCountJob <file>");
            System.exit(1);
        }
        int i = 0;
        for (String str : args) {
            System.out.println("args[" + i + "] = " + str);
            i++;
        }

        SparkConf sparkConf = new SparkConf();
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
/*        try {
            System.out.println("spark.app.id:" + ctx.env().conf().get("spark.app.id"));
            System.out.println("spark.cores.max:" + ctx.env().conf().get("spark.cores.max"));
            System.out.println("spark.driver.port:" + ctx.env().conf().get("spark.driver.port"));
            System.out.println("spark.driver.host:" + ctx.env().conf().get("spark.driver.host"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        JavaRDD<String> lines = ctx.textFile(args[0], 1);

        System.out.print(lines.count());

        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

            @Override
            public Iterator<String> call(String s) throws Exception {
                List<String> list = Arrays.asList(SPACE.split(s));
                return list.iterator();
            }
        });
        JavaPairRDD<String, Integer> ones = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        JavaPairRDD<String, Integer> counts = ones.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer i1, Integer i2) {
                return i1 + i2;
            }
        });

        List<Tuple2<String, Integer>> output = counts.collect();
        for (Tuple2<?, ?> tuple : output) {
            //System.out.println(tuple._1() + ": " + tuple._2());
        }
        counts.saveAsTextFile(args[1]);
        ctx.stop();
    }
}
