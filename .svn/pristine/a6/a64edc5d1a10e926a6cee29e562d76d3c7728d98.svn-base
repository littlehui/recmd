package com.xhui.recmd.spark.services;

import com.xhui.recmd.spark.data.CommodityPair;
import com.xhui.recmd.spark.utils.HbaseApi;
import com.xhui.recmd.spark.utils.HdfsFileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by littlehui on 2016/10/18.
 */
public class HdfsResultCarryer {

    static Logger logger = LoggerFactory.getLogger(HdfsResultCarryer.class);

    private HbaseApi hbaseApi;

    private String hdfsUri;

    private String resultUriPath;

    private static final String PART_SUCCESS_PREFIX = "part-0000";

    private static final String SUCCESS_FLAG = "_SUCCESS";

    private static final String STORE_TABLE = "visitCount";

    private List<String> needReadFilesUris = new ArrayList<>();

    public HdfsResultCarryer(String hdfsUri, String resultUriPath, HbaseApi hbaseApi) {
        this.hbaseApi = hbaseApi;
        this.hdfsUri = hdfsUri;
        this.resultUriPath = resultUriPath;
    }

    public List<CommodityPair> readFromHdfs(String fileUri) {
        List<CommodityPair> commodityPairs = new ArrayList<>();
        try {
            String resultStr = HdfsFileSystem.getFileStream(new Configuration(), hdfsUri, fileUri);
            //TODO 正则匹配出一个 List\
            /**
             * (CommodityPair{ip='111.161.98.61', code='172032338596', visitType='null', score=null, startTimeMills=null, endTimeMills=null},4)
             * (CommodityPair{ip='182.118.25.217', code='172032336845', visitType='null', score=null, startTimeMills=null, endTimeMills=null},2)
             */
            String[] results = resultStr.split("\\n");
            for (String result : results) {
                CommodityPair commodityPair = new CommodityPair(result);
                commodityPairs.add(commodityPair);
            }
            return commodityPairs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createNeedReadFilesUri() {
        try {
            FileStatus[] fileStatuses = HdfsFileSystem.ls(new Configuration(), hdfsUri, resultUriPath);
            if (fileStatuses != null && fileStatuses.length > 0) {
                for (FileStatus fileStatus : fileStatuses) {
                    if (!fileStatus.isDirectory()) {
                        if (!fileStatus.getPath().getName().endsWith(SUCCESS_FLAG)) {
                            StringBuffer needReadFileUri = new StringBuffer();
                            needReadFileUri.append(fileStatus.getPath());
                            needReadFilesUris.add(needReadFileUri.toString());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doCarry() {
        createNeedReadFilesUri();
        if (needReadFilesUris != null && needReadFilesUris.size() > 0) {
            for (String uri : needReadFilesUris) {
                List<CommodityPair> commodityPairs = readFromHdfs(uri);
                addToHBase(commodityPairs);
            }
        }
    }

    private void addToHBase(List<CommodityPair> commodityPairs) {
        if (commodityPairs != null && commodityPairs.size() > 0) {
            for (CommodityPair commodityPair : commodityPairs) {
/*                hbaseApi.addRow(STORE_TABLE, commodityPair.toRowKey(), "count", "commodityCode", commodityPair.getCode());
                hbaseApi.addRow(STORE_TABLE, commodityPair.toRowKey(), "count", "fat", commodityPair.getScore() + "");
                hbaseApi.addRow(STORE_TABLE, commodityPair.toRowKey(), "count", "ip", commodityPair.getIp());
                */
                hbaseApi.addRow(STORE_TABLE, commodityPair.getIp(), "count", commodityPair.getCode(), commodityPair.getScore() + "");
            }
        }
    }

    public static void main(String[] args) {
        String uri="hdfs://10.5.117.109:9000/";   //hdfs 地址
        //String local="C:/Users/Administrator/Desktop/a.txt";  //本地路径
        String resultUri = "hdfs://10.5.117.109:9000/spark/job/result/20160919.result";
        HbaseApi hbaseApi = new HbaseApi("10.5.117.151,10.5.117.152");
        HdfsResultCarryer hdfsResultCarryer = new HdfsResultCarryer(uri, resultUri, hbaseApi);
        hdfsResultCarryer.doCarry();
    }
}
