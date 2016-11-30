/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xhui.recmd.core.bayes.auxiliary;

import java.util.ArrayList;

/**
 * @author Michael Kong
 */
public class NaiveBayes extends Classifier {

    boolean isClassfication[];
    ArrayList<Double> lblClass = new ArrayList<Double>();  //存储目标值的种类
    ArrayList<Integer> lblCount = new ArrayList<Integer>();//存储目标值的个数
    ArrayList<Float> lblProba = new ArrayList<Float>();//存储对应的label的概率
    CountProbility countlblPro;
    /*@ClassListBasedLabel是将训练数组按照 label的顺序来分类存储*/
    ArrayList<ArrayList<ArrayList<Double>>> ClassListBasedLabel = new ArrayList<ArrayList<ArrayList<Double>>>();

    public NaiveBayes() {
    }

    @Override
    /**
     * @train主要完成求一些概率
     * 1.labels中的不同取值的概率f(Yi);  对应28,29行两段代码
     * 2.将训练数组按目标值分类存储   第37行代码

     * */
    public void train(boolean[] isCategory, double[][] features, double[] labels) {
        isClassfication = isCategory;
        countlblPro = new CountProbility(isCategory, features, labels);
        countlblPro.getlblClass(lblClass, lblCount, lblProba);
        ArrayList<ArrayList<Double>> trainingList = countlblPro.UnionFeaLbl(features, labels); //union the features[][] and labels[]
        ClassListBasedLabel = countlblPro.getClassListBasedLabel(lblClass, trainingList);
    }

    @Override
    /**3.在Y的条件下，计算Xi的概率 f(Xi/Y)；
     * 4.返回使得Yi*Xi*...概率最大的那个label的取值
     * */
    public double predict(double[] features) {

        int max_index; //用于记录使概率取得最大的那个索引
        int index = 0; //这个索引是 标识不同的labels 所对应的概率
        ArrayList<Double> pro_ = new ArrayList<Double>(); //这个概率数组是存储features[] 在不同labels下对应的概率
        //依次取不同的label值对应的元祖集合
        for (ArrayList<ArrayList<Double>> elements : ClassListBasedLabel) {
            ArrayList<Double> pro = new ArrayList<Double>();//存同一个label对应的所有概率，之后其中的元素自乘
            double probility = 1.0; //计算概率的乘积

            for (int i = 0; i < features.length; i++) {
                //用于对属性的离散还是连续做判断
                if (isClassfication[i]) {

                    int count = 0;
                    //依次取labels中的所有元祖
                    for (ArrayList<Double> element : elements) {
                        //如果这个元祖的第index数据和b相等，那么就count就加1
                        if (element.get(i).equals(features[i]))
                            count++;
                    }
                    if (count == 0) {
                        pro.add(1 / (double) (elements.size() + 1));
                    } else
                        //统计完所有之后  计算概率值 并加入
                        pro.add(count / (double) elements.size());
                } else {

                    double Sdev;
                    double Mean;
                    double probi = 1.0;
                    Mean = countlblPro.getMean(elements, i);
                    Sdev = countlblPro.getSdev(elements, i);
                    if (Sdev != 0) {
                        probi *= ((1 / (Math.sqrt(2 * Math.PI) * Sdev)) * (Math.exp(-(features[i] - Mean) * (features[i] - Mean) / (2 * Sdev * Sdev))));
                        pro.add(probi);
                    } else
                        pro.add(1.5);

                }
            }
            for (double pi : pro)
                probility *= pi; //将所有概率相乘
            probility *= lblProba.get(index);//最后再乘以一个 Yi
            pro_.add(probility);// 放入pro_ 至此 一个循环结束，
            index++;
        }
        double max_pro = pro_.get(0);
        max_index = 0;


        for (int i = 1; i < pro_.size(); i++) {
            if (pro_.get(i) >= max_pro) {
                max_pro = pro_.get(i);
                max_index = i;
            }
        }
        return lblClass.get(max_index);
    }


    public class CountProbility {
        boolean[] isCatory;
        double[][] features;
        private double[] labels;

        public CountProbility(boolean[] isCategory, double[][] features, double[] labels) {
            this.isCatory = isCategory;
            this.features = features;
            this.labels = labels;
        }

        //获取label中取值情况
        public void getlblClass(ArrayList<Double> lblClass, ArrayList<Integer> lblCount, ArrayList<Float> lblProba) {
            int j = 0;
            for (double i : labels) {
                //如果当前的label不存在于lblClass则加入
                if (!lblClass.contains(i)) {
                    lblClass.add(j, i);
                    lblCount.add(j++, 1);
                } else //如果label中已经存在，就将其计数加1
                {
                    int index = lblClass.indexOf(i);
                    int count = lblCount.get(index);
                    lblCount.set(index, ++count);
                }

            }
            for (int i = 0; i < lblClass.size(); i++) {
//            	System.out.println("值为"+lblClass.get(i)+"的个数有"+lblCount.get(i)+"概率是"+lblCount.get(i)/(float)labels.length);
                lblProba.add(i, lblCount.get(i) / (float) labels.length);
            }

        }

        //将label[]和features[][]合并
        public ArrayList<ArrayList<Double>> UnionFeaLbl(double[][] features, double[] labels) {
            ArrayList<ArrayList<Double>> traingList = new ArrayList<ArrayList<Double>>();
            for (int i = 0; i < features.length; i++) {
                ArrayList<Double> elements = new ArrayList<Double>();
                for (int j = 0; j < features[i].length; j++) {
                    elements.add(j, features[i][j]);
                }
                elements.add(features[i].length, labels[i]);
                traingList.add(i, elements);

            }
            return traingList;
        }

        /*将测试数组按label的值分类存储*/
        public ArrayList<ArrayList<ArrayList<Double>>> getClassListBasedLabel(ArrayList<Double> lblClass, ArrayList<ArrayList<Double>> trainingList) {
            ArrayList<ArrayList<ArrayList<Double>>> ClassListBasedLabel = new ArrayList<ArrayList<ArrayList<Double>>>();
            for (double num : lblClass) {
                ArrayList<ArrayList<Double>> elements = new ArrayList<ArrayList<Double>>();
                for (ArrayList<Double> element : trainingList) {
                    if (element.get(element.size() - 1).equals(num))
                        elements.add(element);
                }
                ClassListBasedLabel.add(elements);
            }
            return ClassListBasedLabel;
        }

        public double getMean(ArrayList<ArrayList<Double>> elements, int index) {
            double sum = 0.0;
            double Mean;

            for (ArrayList<Double> element : elements) {
                sum += element.get(index);

            }
            Mean = sum / (double) elements.size();
            return Mean;
        }

        public double getSdev(ArrayList<ArrayList<Double>> elements, int index) {
            double dev = 0.0;
            double Mean;
            Mean = getMean(elements, index);
            for (ArrayList<Double> element : elements) {
                dev += Math.pow((element.get(index) - Mean), 2);
            }
            dev = Math.sqrt(dev / elements.size());
            return dev;
        }
    }
}

