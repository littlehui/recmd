package com.xhui.recmd.core.hao;

import com.xhui.recmd.core.union.ColumnGoal;
import com.xhui.recmd.core.union.IndividualCommonPoint;
import com.xhui.recmd.spark.utils.HbaseApi;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;

import java.util.List;

/**
 * Created by littlehui on 2016/11/1.
 */
public class IpToIpDisHao {

    private static final String IP_TO_IP_DIS_TABLE = "ipToIpDis";

    public void addToHbase(IndividualCommonPoint individualInterestPoint) {
        List<ColumnGoal> columnGoals = individualInterestPoint.getColumnGoals();
        if (columnGoals != null && columnGoals.size() > 0) {
            for (ColumnGoal columnGoal : columnGoals) {
                HbaseApi.addRow(IP_TO_IP_DIS_TABLE, individualInterestPoint.getIp() + "", "ip", columnGoal.getColumn(), columnGoal.getGoal() + "");
            }
        }
    }

    public IndividualCommonPoint getByIP(String ip) {
        IndividualCommonPoint individualInterestPoint = new IndividualCommonPoint();
        Cell[] cells = HbaseApi.getRow(IP_TO_IP_DIS_TABLE, ip);
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
