package com.xhui.recmd.core.hao;

import com.xhui.recmd.core.union.ColumnGoal;
import com.xhui.recmd.core.union.IndividualCommonPoint;
import com.xhui.recmd.spark.utils.HbaseApi;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by littlehui on 2016/11/2.
 */
public class RecmdResultHao {

    private static final String RECMD_RESULT_TABLE = "recmdResult";


    public void addToHbase(IndividualCommonPoint individualInterestPoint) {
        List<ColumnGoal> columnGoals = individualInterestPoint.getColumnGoals();
        if (columnGoals != null && columnGoals.size() > 0) {
            for (ColumnGoal columnGoal : columnGoals) {
                HbaseApi.addRow(RECMD_RESULT_TABLE, individualInterestPoint.getIp() + "", "result", columnGoal.getColumn(), columnGoal.getGoal() + "");
            }
        }
    }

    public List<ColumnGoal> getByIP(String ip) {
        IndividualCommonPoint individualInterestPoint = new IndividualCommonPoint();
        Cell[] cells = HbaseApi.getRow(RECMD_RESULT_TABLE, ip);
        if (cells != null && cells.length > 0) {
            for (Cell cell : cells) {
                String valueStr = new String(CellUtil.cloneValue(cell));
                Double value = new Double(valueStr);
                ColumnGoal columnGoal = new ColumnGoal(new String(CellUtil.cloneQualifier(cell)) + "", value);
                individualInterestPoint.addColumnGoal(columnGoal);
                individualInterestPoint.setIp(new String(CellUtil.cloneRow(cell)));
            }
        }
        return individualInterestPoint.getColumnGoals();
    }
}
