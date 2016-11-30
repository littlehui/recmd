package com.xhui.recmd.zk.services;

import com.xhui.recmd.core.hao.IpToIpDisHao;
import com.xhui.recmd.core.union.ColumnGoal;
import com.xhui.recmd.core.union.IndividualCommonPoint;
import com.xhui.recmd.zk.bean.IpToIpDis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by littlehui on 2016/11/20 0020.
 */
public class IpToIpDisService {

    IpToIpDisHao ipToIpDisHao = new IpToIpDisHao();

    public List<IpToIpDis> queryByIp(String ip) {
        IndividualCommonPoint individualCommonPoint = ipToIpDisHao.getByIP(ip);
        return toIpToIpDis(individualCommonPoint);
    }

    private List<IpToIpDis> toIpToIpDis(IndividualCommonPoint individualCommonPoint) {
        List<ColumnGoal> columnGoals = individualCommonPoint.getColumnGoals();
        List<IpToIpDis> ipToIpDises = new ArrayList<>();
        if (columnGoals != null && columnGoals.size() > 0) {
            for (ColumnGoal columnGoal : columnGoals) {
                IpToIpDis ipToIpDis = new IpToIpDis();
                ipToIpDis.setSrcIp(individualCommonPoint.getIp());
                ipToIpDis.setDesIp(columnGoal.getColumn());
                ipToIpDis.setIpToIpDis(columnGoal.getGoal());
                ipToIpDises.add(ipToIpDis);
            }
        }
        return ipToIpDises;
    }
 }
