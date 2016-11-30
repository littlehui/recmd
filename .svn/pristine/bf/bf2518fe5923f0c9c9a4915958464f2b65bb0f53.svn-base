package com.xhui.recmd.core.spark.tests;

import com.xhui.recmd.core.hao.InvidualInterestPointHao;
import com.xhui.recmd.core.service.RecmdCoreService;
import com.xhui.recmd.core.union.IndividualCommonPoint;
import com.xhui.recmd.spark.services.HdfsResultCarryer;
import com.xhui.recmd.spark.utils.HbaseApi;
import org.junit.Test;

import java.util.List;

/**
 * Created by littlehui on 2016/11/2.
 */
public class RecmdCoreServiceTests {

    RecmdCoreService recmdCoreService = new RecmdCoreService();

    InvidualInterestPointHao invidualInterestPointHao = new InvidualInterestPointHao();

    @Test
    public void washLog() {
        HbaseApi hbaseApi = new HbaseApi("10.5.117.151,10.5.117.152");
        Long startMills = System.currentTimeMillis();
        System.out.println("washLog 开始：时间：" + startMills);
        HdfsResultCarryer hdfsResultCarryer = new HdfsResultCarryer("hdfs://10.5.117.109:9000", "hdfs://10.5.117.109:9000/spark/job/result/20160918.result", hbaseApi);
        hdfsResultCarryer.doCarry();
        System.out.println("wasLog 结束：花费时间：" + (System.currentTimeMillis() - startMills) / 1000 + "秒");
    }

    @Test
    public void doKmeans() {
        Long startTime = System.currentTimeMillis();
        System.out.println("开始 进行聚类时间：" + startTime);
        List<IndividualCommonPoint> individualInterestPoints = invidualInterestPointHao.getAllIndivialInterestPoints();
        recmdCoreService.doKMeansCluser(individualInterestPoints);
        Long entTime = System.currentTimeMillis();
        System.out.println("结束 时间：" + entTime + "耗时：" + (entTime-startTime)/1000 + "秒");
    }

    @Test
    public void  ipToIpIndividual() {
        recmdCoreService.ipToIpIndividual();
    }

    @Test
    public void findAndSort() {
        recmdCoreService.findAndSort("122.244.222.34");
    }
}
