package com.xhui.recmd.core.hao;

import com.xhui.recmd.core.union.ColumnGoal;
import com.xhui.recmd.core.union.IndividualCommonPoint;
import com.xhui.recmd.spark.utils.HbaseApi;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by littlehui on 2016/10/19.
 */
public class ClassifierHao {

    private static final String CLASS_TABLE = "classifier";

    private static final String CLASS_FAMILY = "ipClass";

    public void addToClassifier(String classifierKey, IndividualCommonPoint individualInterestPoint) {
        HbaseApi.addRow(CLASS_TABLE, classifierKey, CLASS_FAMILY, individualInterestPoint.getIp(), individualInterestPoint.getClassCoreDis() + "");
    }

    public void addAClassifier(List<IndividualCommonPoint> individualInterestPoints) {
        if (individualInterestPoints != null && individualInterestPoints.size() > 0) {
            for (IndividualCommonPoint individualInterestPoint : individualInterestPoints) {
                /**
                 * key          ip(10.5.32.97)  ip(1.2.32.32)   ....
                 * 121141       0.1             0.3             ....
                 */
                HbaseApi.addRow(CLASS_TABLE, individualInterestPoints.hashCode() + "", CLASS_FAMILY, individualInterestPoint.getIp(), individualInterestPoint.getClassCoreDis() + "");
            }
            //保存类别关联的所有Column值
        }
    }

    public List<IndividualCommonPoint> getByClassfier(String rowKey) {
        //HbaseApi.getRow()
        return null;
    }

    public List<IndividualCommonPoint> queryAll() {
        ResultScanner resultScanner = HbaseApi.getAllRows(CLASS_TABLE);
        List<IndividualCommonPoint> individualInterestPoints = new ArrayList<>();
        for (Result result : resultScanner) {
            IndividualCommonPoint individualInterestPoint = new IndividualCommonPoint();
            for (Cell cell : result.rawCells()) {
                String valueStr = new String(CellUtil.cloneValue(cell));
                Double value = new Double(valueStr);
                ColumnGoal columnGoal = new ColumnGoal(new String(CellUtil.cloneQualifier(cell)) + "", value);
                individualInterestPoint.addColumnGoal(columnGoal);
                individualInterestPoint.setIp(new String(CellUtil.cloneRow(cell)));
            }
            individualInterestPoints.add(individualInterestPoint);
        }
        return individualInterestPoints;
    }

    public String getClassifierIps(String ip) {
        return "";
    }
}
