package com.xhui.recmd.zk.services;

import com.xhui.recmd.core.hao.InvidualInterestPointHao;
import com.xhui.recmd.core.union.ColumnGoal;
import com.xhui.recmd.core.union.IndividualCommonPoint;
import com.xhui.recmd.zk.bean.VisitCountVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by littlehui on 2016/11/20 0020.
 */
public class VisitCountService {

    private InvidualInterestPointHao invidualInterestPointHao = new InvidualInterestPointHao();

    public List<VisitCountVo> queryByIP(String Ip) {
        IndividualCommonPoint individualCommonPoint = invidualInterestPointHao.getByIP(Ip);
        return toVisitCoutVo(individualCommonPoint);
    }

    public List<VisitCountVo> toVisitCoutVo(IndividualCommonPoint individualCommonPoint) {
        List<ColumnGoal> columnGoals = individualCommonPoint.getColumnGoals();
        if (columnGoals == null || columnGoals.size() < 0) {
            return null;
        }
        List<VisitCountVo> visitCountVos = new ArrayList<>();
        for (ColumnGoal columnGoal : columnGoals) {
            VisitCountVo visitCountVo = new VisitCountVo();
            visitCountVo.setIp(individualCommonPoint.getIp());
            visitCountVo.setVisitResource(columnGoal.getColumn());
            visitCountVo.setVisitValue(columnGoal.getGoal());
            visitCountVos.add(visitCountVo);
        }
        return visitCountVos;
    }

    public List<VisitCountVo> queryAll() {
        List<IndividualCommonPoint> individualCommonPoints = invidualInterestPointHao.getAllIndivialInterestPoints();
        List<VisitCountVo> result = new ArrayList<>();
        if (individualCommonPoints != null && individualCommonPoints.size() > 0) {
            for (IndividualCommonPoint individualCommonPoint : individualCommonPoints) {
                result.addAll(toVisitCoutVo(individualCommonPoint));
            }
        }
        return result;
    }
}
