package com.xhui.recmd.core.count;

/**
 * Created by littlehui on 2016/9/19.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

// k-means cluster
public class UserBehaviourClusterKmeans {
    private Instances instances;
    private String sourceFile;
    private String targetFile;
    private int kNumber;
    private int[] assignment;

    public UserBehaviourClusterKmeans(String sourceFile, String targetFile, int kNumber) {
        this.sourceFile = sourceFile;
        this.targetFile = targetFile;
        this.kNumber = kNumber;
    }

    public void loadData() throws Exception {
        DataSource dataSource = new DataSource(sourceFile);
        instances = dataSource.getDataSet();
    }

    public void preprocess() throws Exception {
        Remove remove = new Remove();
        remove.setAttributeIndices("1");
        remove.setInputFormat(instances);
        instances = Filter.useFilter(instances, remove);
    }

    public void cluster() throws Exception {
        SimpleKMeans kmeans = new SimpleKMeans();
        kmeans.setPreserveInstancesOrder(true);
        kmeans.setNumClusters(kNumber);
        kmeans.buildClusterer(instances);
        assignment = kmeans.getAssignments();
    }

    public void writeResult() throws Exception {
        FileReader fr = null;
        BufferedReader br = null;

        FileWriter fw = null;
        BufferedWriter bw = null;

        String line = null;
        int j = 0;

        try {
            fr = new FileReader(sourceFile);
            br = new BufferedReader(fr);
            fw = new FileWriter(targetFile);
            bw = new BufferedWriter(fw);

            line = br.readLine();
            bw.write(line + ",clusterID\n");
            while ((line = br.readLine()) != null) {
                bw.write(line + "," + assignment[j++] + "\n");
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
        }
    }

    public void process() throws Exception {
        loadData();
        preprocess();
        cluster();
        writeResult();
    }
}