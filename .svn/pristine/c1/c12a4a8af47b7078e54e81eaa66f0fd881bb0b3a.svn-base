package com.xhui.recmd.core.count;

/**
 * Created by littlehui on 2016/9/19.
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

public class UserBehaviourTrain {
    private Instances instances;
    private String sourceFile;
    private String targetModelFile;
    private Classifier classifier;

    public UserBehaviourTrain(String sourceFile, String targetModelFile) {
        this.sourceFile = sourceFile;
        this.targetModelFile = targetModelFile;
    }

    public void loadData() throws Exception {
        DataSource source = new DataSource(sourceFile);
        instances = source.getDataSet();
    }

    public void preprocess() throws Exception {
        Remove remove = new Remove();
        remove.setAttributeIndices("1");
        remove.setInputFormat(instances);
        instances = Filter.useFilter(instances, remove);

        NumericToNominal ntn = new NumericToNominal();
        ntn.setAttributeIndices("last");
        ntn.setInputFormat(instances);
        instances = Filter.useFilter(instances, ntn);

        instances.setClassIndex(instances.numAttributes() - 1);
    }

    public void train() throws Exception {
        classifier = new NaiveBayes();
        classifier.buildClassifier(instances);
    }

    public void writeModel() throws FileNotFoundException, IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(targetModelFile));
            oos.writeObject(classifier);
            oos.flush();
        } finally {
            if (oos != null) oos.close();
        }
    }

    public void process() throws Exception {
        loadData();
        preprocess();
        train();
        writeModel();
    }
}