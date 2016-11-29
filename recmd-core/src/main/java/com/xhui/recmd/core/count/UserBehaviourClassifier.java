package com.xhui.recmd.core.count;

/**
 * Created by littlehui on 2016/9/19.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

// classifier new coming data
public class UserBehaviourClassifier {
    private Classifier classifier;
    private String modelFile;
    private String trainFile;
    private String templateFile;
    private Instances template;
    private String categoryFile;
    public static ArrayList<String> category = new ArrayList<String>();

    public UserBehaviourClassifier(String modelFile, String trainFile, String templateFile,
                                   String categoryFile) throws Exception {
        this.modelFile = modelFile;
        this.trainFile = trainFile;
        this.templateFile = templateFile;
        this.categoryFile = categoryFile;

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(modelFile));
        classifier = (Classifier) ois.readObject();
        ois.close();
        initial();
    }

    public void getTemplateFile() throws IOException {
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        BufferedWriter bw = null;

        String line = null;
        int count = 0;
        try {
            fr = new FileReader(trainFile);
            br = new BufferedReader(fr);
            fw = new FileWriter(templateFile);
            bw = new BufferedWriter(fw);
            while ((line = br.readLine()) != null) {
                bw.write(line + "\n");
                if (count++ > 2) break;
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

    public void initial() throws Exception {
        getTemplateFile();

        DataSource source = new DataSource(templateFile);
        template = source.getDataSet();
        Remove remove = new Remove();
        remove.setAttributeIndices("1");
        remove.setInputFormat(template);
        template = Filter.useFilter(template, remove);
        NumericToNominal ntn = new NumericToNominal();
        ntn.setAttributeIndices("last");
        ntn.setInputFormat(template);
        template = Filter.useFilter(template, ntn);
        template.setClassIndex(template.numAttributes() - 1);
        // get category
        readCategory(categoryFile);
    }

    public int classify(Map<String, Integer> urlMap) throws Exception {
        // value only attribute
        ArrayList<Double> value = new ArrayList<Double>();
        value = Preprocess(urlMap);
        Instance instance = new DenseInstance(value.size());
        for (int i = 0; i < value.size(); i++) {
            instance.setValue(i, value.get(i));
        }
        instance.setDataset(template);
        return (int) classifier.classifyInstance(instance);
    }


    public void readCategory(String file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        for (String s : br.readLine().split(",")) {
            category.add(s);
        }
        br.close();
        fr.close();
    }


    public static ArrayList<Double> Preprocess(Map<String, Integer> urlMap) {
        ArrayList<Double> result = new ArrayList<Double>();
        for (String s : category) {
            if (urlMap.get(s) == null) {
                result.add(0.0);
            } else {
                result.add((double) urlMap.get(s));
            }
        }
        ArrayList<Double> answer = cosin(result);
        return answer;

    }


    public static ArrayList<Double> cosin(ArrayList<Double> data) {
        double sum = 0;
        for (Double d : data) {
            sum += d * d;
        }
        sum = Math.sqrt(sum);
        ArrayList<Double> answer = new ArrayList<Double>();
        for (Double d : data) {
            answer.add(d / sum);
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {

        String filePath = "./";

        UserBehaviourClassifier classifier =
                new UserBehaviourClassifier(filePath + "trainResult.model", filePath + "Kmeans.csv",
                        filePath + "template.csv", filePath + "category.csv");

        // new coming data
        Map<String, Integer> urlmap = new HashMap<String, Integer>();
        // just for test,put data here
            urlmap.put("5398", 1);

        long startTime = System.currentTimeMillis();
        classifier.classify(urlmap);
        long stopTime = System.currentTimeMillis();

        System.out.println(classifier.classify(urlmap));
        System.out.println("cost time= " + (stopTime - startTime));
    }
}