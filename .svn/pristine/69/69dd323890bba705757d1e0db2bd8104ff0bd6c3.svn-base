package com.xhui.recmd.core.bayes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by littlehui on 2016/10/19.
 */
public class Bayes {

    ArffUtil util = new ArffUtil();

    private List<String> attributeList = null;

    private List<String[]> dataList = null;

    private String decAttributeName = null;

    private int decAttributeIndex = -1;

    private Map<String, List<String[]>> seperatedDataTable = null;

    public Bayes(String decAttributeName, List<String> attributeList, List<String[]> dataList) {
        this.attributeList = attributeList;
        this.dataList = dataList;
        this.decAttributeName = decAttributeName;
        this.decAttributeIndex = util.getValueIndex(decAttributeName, this.attributeList);
        this.seperatedDataTable = seperateDataList(dataList);
    }

    private Map<String, List<String[]>> seperateDataList(List<String[]> dataList) {
        Map<String, List<String[]>> map = new HashMap<String, List<String[]>>();
        for(String[] arr : dataList) {
            if(decAttributeIndex >= 0 && decAttributeIndex < arr.length) {
                String currentKey = arr[decAttributeIndex];
                if(map.containsKey(currentKey)) {
                    List<String[]> tempList = map.get(currentKey);
                    tempList.add(arr);
                    map.put(currentKey, tempList);
                } else {
                    List<String[]> tempList = new ArrayList<String[]>();
                    tempList.add(arr);
                    map.put(currentKey , tempList);
                }
            }
        }

        return map;
    }

    public Boolean predict(Map<String, String> predictData, String targetDecAttributeValue) {
        if(predictData.containsKey(decAttributeName)) predictData.remove(decAttributeName);

        List<String[]> positiveDataTable = new ArrayList<String[]>();
        if(seperatedDataTable.containsKey(targetDecAttributeValue)) {
            positiveDataTable = seperatedDataTable.get(targetDecAttributeValue);
        }

        double resultP = 1.;

        // Step 1: 逐个属性的比率进行计算
        // 即： 计算 P(Attr=Value|Y=true) / P(Attr=Value|Y=false) 的值
        for(String attrName : predictData.keySet()) {
            String attrValue = predictData.get(attrName);
            int attrIndex = util.getValueIndex(attrName, attributeList);
            int attrPositiveCount = 0;
            int attrNegativeCount = 0;

            for(String[] arr : dataList) {
                if(arr[attrIndex].equals(attrValue)) {
                    if(arr[decAttributeIndex].equals(targetDecAttributeValue)) {
                        attrPositiveCount++;
                    } else {
                        attrNegativeCount++;
                    }
                }
            }
            double temp =  (attrPositiveCount / (double)positiveDataTable.size() ) /
                    (attrNegativeCount / (double)(dataList.size() - positiveDataTable.size()));
            resultP *= temp;
        }
        // 最后计算 P(Y=true) / P(Y=false)
        resultP *= positiveDataTable.size() / (double)(dataList.size() - positiveDataTable.size());
        System.out.println(resultP);
        if(resultP > 1) {
            return true;
        } else {
            return false;
        }
    }
}
