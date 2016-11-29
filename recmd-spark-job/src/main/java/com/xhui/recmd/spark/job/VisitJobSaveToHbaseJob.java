package com.xhui.recmd.spark.job;

import com.xhui.recmd.spark.data.CommodityPair;
import com.xhui.recmd.spark.services.OrderCommodityMatcher;
import com.xhui.recmd.spark.services.VisitCommodityMatcher;
import com.xhui.recmd.spark.services.VisitIpMather;
import com.xhui.recmd.spark.services.VisitTimeMather;
import com.xhui.recmd.spark.utils.HbaseApi;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * Created by littlehui on 2016/9/12.
 * 此类SAVE的时候不成功跟hbase连接的有问题。
 * 当前版本 hbase1.2.2 spark版本2.0存在不兼容现象
 */
public class VisitJobSaveToHbaseJob {

    static Logger logger = LoggerFactory.getLogger(VisitJobSaveToHbaseJob.class);

    private final static String VISIT_PRIFIX = "http://mai.17173.com/commodity/visual/detail.html?commodityCode=";

    private final static String SAVE_FILE_PRE = "hdfs://keyword.17173.com:9000/spark/job/result";

    private final static String SOURCE_PRE = "hdfs://keyword.17173.com:9000/spark/job/data";

    private final static String COMMODITY_REGEX = "http://mai\\.17173\\.com/commodity/visual/detail\\.html\\?commodityCode=\\d{12}";

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
/*
        System.out.println("开始mapToPair时间：" + dateFormat.format(new Date(startMapPairMills)));
*/
        final JavaPairRDD<CommodityPair, Integer> pairs = lines.mapToPair(new PairFunction<String, CommodityPair, Integer>() {
            @Override
            public Tuple2<CommodityPair, Integer> call(String s) {
                callCount++;
                int fat = 1;
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
        callCount = 0;
        //相加并且计算 CommodityPair.score
        JavaPairRDD<CommodityPair, Integer> counts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer i1, Integer i2) {
                return i1 + i2;
            }
        });

        //counts.saveAsTextFile(SAVE_FILE_PRE + "/" + args[0] + ".result");


        //Configuration saveConfiguration = ctx.hadoopConfiguration();
        //sc.hadoopConfiguration.set("hbase.zookeeper.quorum ","zkNode1,zkNode2,zkNode3")
/*        Configuration saveConfiguration = HBaseConfiguration.create();
        saveConfiguration.set("hbase.zookeeper.quorum", "10.5.117.151,10.5.117.152");
        saveConfiguration.set("hbase.zookeeper.property.clientPort", "2181");
        saveConfiguration.set("zookeeper.znode.parent", "/hbase");
        saveConfiguration.set("hbase.master", "10.5.117.109:16000");
        saveConfiguration.set(TableInputFormat.INPUT_TABLE, "visitCount");
        counts.saveAsNewAPIHadoopDataset(saveConfiguration);*/

/*        final HbaseApi hbaseApi = new HbaseApi("10.5.117.151,10.5.117.152");
        counts.foreach(new VoidFunction<Tuple2<CommodityPair, Integer>>() {
            @Override
            public void call(Tuple2<CommodityPair, Integer> commodityPairIntegerTuple2) throws Exception {
                CommodityPair commodityPair = commodityPairIntegerTuple2._1();
                Integer fat = commodityPairIntegerTuple2._2();
                String rowKey = commodityPair.toString() + "_" + fat;
                hbaseApi.addRow("visitCount", rowKey, "count", "commodityCode", commodityPair.getCode());
                hbaseApi.addRow("visitCount", rowKey, "count", "fat", fat + "");
                hbaseApi.addRow("visitCount", rowKey, "count", "ip", commodityPair.getIp());
            }
        });

                hbaseApi.closed();
*/
        counts.foreachPartition(new VoidFunction<Iterator<Tuple2<CommodityPair, Integer>>>() {
            @Override
            public void call(Iterator<Tuple2<CommodityPair, Integer>> tuple2Iterator) throws Exception {
                final HbaseApi hbaseApi = new HbaseApi("10.5.117.151,10.5.117.152");
                while (tuple2Iterator.hasNext()) {
                    Tuple2<CommodityPair, Integer> tuple2 = tuple2Iterator.next();
                    CommodityPair commodityPair = tuple2._1();
                    Integer fat = tuple2._2();
                    logger.info("加入 commodityPair:" + commodityPair.toString());
                    logger.info("加入 fat :" + fat);
                    logger.info("visitCount表存在嗎?: " + hbaseApi.isTableExists("visitCount"));
                    String rowKey = commodityPair.getIp() + "_" + fat;
                    hbaseApi.addRow("visitCount", rowKey, "count", "commodityCode", commodityPair.getCode());
                    hbaseApi.addRow("visitCount", rowKey, "count", "fat", fat + "");
                    hbaseApi.addRow("visitCount", rowKey, "count", "ip", commodityPair.getIp());

                }
            }
        });
        //ctx.newAPIHadoopRDD(saveConfiguration, TableInputFormat.class, CommodityPair.class, Integer.class);
        ctx.stop();
    }
}