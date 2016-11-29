package com.xhui.recmd.spark.job;

import com.xhui.recmd.spark.data.CommodityPair;
import com.xhui.recmd.spark.services.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by littlehui on 2016/9/8.
 */
public class VisitCountJob {

    private final static String VISIT_PRIFIX = "http://mai.17173.com/commodity/visual/detail.html?commodityCode=";

    private final static String SAVE_FILE_PRE = "hdfs://keyword.17173.com:9000/spark/job/result";

    private final static String SOURCE_PRE = "hdfs://keyword.17173.com:9000/spark/job/data";

    private final static String COMMODITY_REGEX = "http://mai\\.17173\\.com/commodity/visual/detail\\.html\\?commodityCode=\\d{12}";

    private final static String MATERIAL_COMMODITY_REGEX = "http://mai\\.17173\\.com/commodity/materil/detail\\.html\\?commodityCode=\\d{12}";

    private final static String COMMODITY_ORDER_REGEX = "http://mai\\.17173\\.com/commodity/visual/orderstep\\.html\\?commodityCode=\\d{12}";

    private final static String COMMODITY_ORDER_PREFIX = "http://mai.17173.com/commodity/visual/orderstep.html?commodityCode=";

    private final static String IP_REGEX = "\"\\d+\\.\\d+\\.\\d+\\.\\d+\" \\[\\d+/.+]";

    private final static String TIME_REGEX = "\\[\\d+/.+]";

    public static Integer callCount = 0;

    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) throws Exception {
        SparkConf sparkConf = new SparkConf();
        JavaSparkContext ctx = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = ctx.textFile(SOURCE_PRE + "/" + args[0], 1);
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
        Long startMapPairMills = System.currentTimeMillis();
        System.out.println("开始mapToPair时间：" + dateFormat.format(new Date(startMapPairMills)));
        final JavaPairRDD<CommodityPair, Integer> pairs = lines.mapToPair(new PairFunction<String, CommodityPair, Integer>() {
            @Override
            public Tuple2<CommodityPair, Integer> call(String s) {
                callCount++;
                int fat = 1;
                //一行一行分析
                CommodityPair keyPair = new CommodityPair();
                VisitCommodityMatcher visitCommoityMatcher = new VisitCommodityMatcher(COMMODITY_REGEX);
                String commodityCode = visitCommoityMatcher.mather(s);
                if (commodityCode == null) {
                    OrderCommodityMatcher orderMatcher = new OrderCommodityMatcher(COMMODITY_ORDER_REGEX);
                    commodityCode = orderMatcher.mather(s);
                    if (commodityCode != null) {
                        fat = 2;
                    }
                }
                if (commodityCode == null) {
                    MaterialCommodityMather materialCommodityMather = new MaterialCommodityMather(MATERIAL_COMMODITY_REGEX);
                    commodityCode = materialCommodityMather.mather(s);
                }
                if (commodityCode != null && !"".equals(commodityCode)) {
                    keyPair.setCode(commodityCode);
                    VisitIpMather visitIpMather = new VisitIpMather(IP_REGEX);
                    String ip = visitIpMather.mather(s);
                    VisitTimeMather visitTimeMather = new VisitTimeMather(TIME_REGEX);
                    String time = visitTimeMather.mather(s);
                    keyPair.setIp(ip);
                }
                return new Tuple2<CommodityPair, Integer>(keyPair, fat);
            }
        });
        Long endMapPairMills = System.currentTimeMillis();
        callCount = 0;
        //相加并且计算 CommodityPair.score
        Long startReduceMills = System.currentTimeMillis();
        JavaPairRDD<CommodityPair, Integer> counts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer i1, Integer i2) {
                return i1 + i2;
            }
        });
        Long endReduceMills = System.currentTimeMillis();
        String fileName = args[0].substring(args[0].lastIndexOf("/"), args[0].indexOf("_"));
        counts.saveAsTextFile(SAVE_FILE_PRE + "/" + fileName + ".result");
        ctx.stop();
    }
}
