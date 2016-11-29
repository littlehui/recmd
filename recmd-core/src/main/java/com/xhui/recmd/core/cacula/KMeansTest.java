package com.xhui.recmd.core.cacula;

import java.util.ArrayList;

/**
 * Created by littlehui on 2016/10/11 0011.
 */
public class KMeansTest {
    public  static void main(String[] args)
    {
        //初始化一个Kmean对象，将k置为10
        Kmeans k=new Kmeans(10);
        ArrayList<float[]> dataSet=new ArrayList<float[]>();

        dataSet.add(new float[]{1,2});
        dataSet.add(new float[]{3,3});
        dataSet.add(new float[]{3,4});
        dataSet.add(new float[]{5,6});
        dataSet.add(new float[]{8,9});
        dataSet.add(new float[]{4,5});
        dataSet.add(new float[]{6,4});
        dataSet.add(new float[]{3,9});
        dataSet.add(new float[]{5,9});
        dataSet.add(new float[]{4,2});
        dataSet.add(new float[]{1,9});
        dataSet.add(new float[]{7,8});
        //设置原始数据集
        k.setDataSet(dataSet);
        //执行算法
        k.execute();
        //得到聚类结果
        ArrayList<ArrayList<float[]>> cluster=k.getCluster();
        //查看结果
        for(int i=0;i<cluster.size();i++)
        {
            k.printDataArray(cluster.get(i), "cluster["+i+"]");
        }

    }
}
