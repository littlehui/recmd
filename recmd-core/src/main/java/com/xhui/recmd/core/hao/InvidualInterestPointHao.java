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
public class InvidualInterestPointHao {

    private static final String DEFAULT_VISIT_TABLE = "visitCount";

    public List<IndividualCommonPoint> getAllIndivialInterestPoints() {
        List<IndividualCommonPoint> individualInterestPoints = new ArrayList<>();
        ResultScanner resultScanner = HbaseApi.getAllRows(DEFAULT_VISIT_TABLE);
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

    public IndividualCommonPoint getByIP(String ip) {
        IndividualCommonPoint individualInterestPoint = new IndividualCommonPoint();
        Cell[] cells = HbaseApi.getRow(DEFAULT_VISIT_TABLE, ip);
        if (cells != null && cells.length > 0) {
            for (Cell cell : cells) {
                String valueStr = new String(CellUtil.cloneValue(cell));
                Double value = new Double(valueStr);
                ColumnGoal columnGoal = new ColumnGoal(new String(CellUtil.cloneQualifier(cell)) + "", value);
                individualInterestPoint.addColumnGoal(columnGoal);
                individualInterestPoint.setIp(new String(CellUtil.cloneRow(cell)));
            }
        }
        return individualInterestPoint;
    }
}
