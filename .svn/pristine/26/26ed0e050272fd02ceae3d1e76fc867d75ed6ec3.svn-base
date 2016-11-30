package com.xhui.recmd.core.union;

import java.util.*;

/**
 * Created by littlehui on 2016/9/11 0011.
 */
public class KMeansCluster {

    /**
     * 所有数据列表
     */
    private List<IndividualCommonPoint> individualPoints = new ArrayList();

    /**
     * 初始化列表
     */
    private ArraySet<IndividualCommonPoint> initIndividual;

    /**
     * 需要纳入kmeans列名称 用set便于去重
     */
    private Set<String> needDisColumns = new HashSet<>();

    /**
     * 分类数
     */
    private int k = 1;

    public KMeansCluster() {

    }

    public ArraySet<IndividualCommonPoint> getInitIndividual() {
        return initIndividual;
    }

    public KMeansCluster(List<IndividualCommonPoint> list, int k) {
        this.individualPoints = list;
        this.k = k;
        initIndividual = new ArraySet<>();
        //初始化需要进行计算的 列信息
        for (IndividualCommonPoint userCommodityPair : individualPoints) {
            needDisColumns.addAll(userCommodityPair.keySet());
        }
        //初始化中心(随机选择) 并且 选择的初始化中心 都必须 不同。
        Random random = new Random();
        while (initIndividual.size() < k) {
            Integer index = random.nextInt(individualPoints.size());
            initIndividual.add(individualPoints.get(index));
        }
    }

    public List<IndividualCommonPoint>[] cluster() {
        List<IndividualCommonPoint>[] results = new ArrayList[k];
        boolean centerchange = true;
        while (centerchange) {
            centerchange = false;
            for (int i = 0; i < k; i++) {
                results[i] = new ArrayList<IndividualCommonPoint>();
            }
            for (int i = 0; i < individualPoints.size(); i++) {
                IndividualCommonPoint p = individualPoints.get(i);
                double[] dists = new double[k];
                for (int j = 0; j < initIndividual.size(); j++) {
                    IndividualCommonPoint initP = initIndividual.get(j);
                    /* 计算距离 */
                    double dist = distanceValue(initP, p);
                    dists[j] = dist;
                }
                //有个BUG 如果dists 为 [xx,xx,0.0,0.0]此时 会被归入 result[2],result[3]没值。
                //既 聚类点如果有两个一样的话，聚类K可能小于最终的聚类数目
                int dist_index = computOrder(dists);
                p.setClassCoreDis(dists[dist_index]);
                results[dist_index].add(p);
            }

            for (int i = 0; i < k; i++) {
                IndividualCommonPoint player_new = findNewCenter(results[i]);
                IndividualCommonPoint player_old = initIndividual.get(i);
                if (!isColumnGoalsEquals(player_new, player_old)) {
                    centerchange = true;
                    initIndividual.set(i, player_new);
                }
            }
        }
        return results;
    }

    /**
     * 比较分类信息是否一致
     *
     * @param p1
     * @param p2
     * @return
     */
    public boolean isColumnGoalsEquals(IndividualCommonPoint p1, IndividualCommonPoint p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.equals(p2);
    }

    /**
     * 得到新聚类中心对象
     *
     * @param ps
     * @return
     */
    public IndividualCommonPoint findNewCenter(List<IndividualCommonPoint> ps) {
        double[] ds = new double[needDisColumns.size()];
        IndividualCommonPoint centPair = new IndividualCommonPoint();
        for (IndividualCommonPoint userCommodityPair : ps) {
            int i = 0;
            for (String column : needDisColumns) {
                Double oldValue = userCommodityPair.get(column);
                oldValue = (oldValue == null ? 0 : oldValue);
                ds[i] += oldValue;
                //不变的
                //userCommodityPair.updateColumnGoal(column, oldValue, newValue);
                i++;
            }
        }
        int j = 0;
        for (String column : needDisColumns) {
            ds[j] = ds[j] / ps.size();
            centPair.addColumnGoal(new ColumnGoal(column, ds[j]));
            j++;
        }
        return centPair;
    }

    /**
     * 得到最短距离，并返回最短距离索引
     *
     * @param dists
     * @return
     */
    public int computOrder(double[] dists) {
        double min = 0;
        int index = 0;
        for (int i = 0; i < dists.length - 1; i++) {
            double dist0 = dists[i];
            if (i == 0) {
                min = dist0;
                index = 0;
            }
            double dist1 = dists[i + 1];
            if (min > dist1) {
                min = dist1;
                index = i + 1;
            }
        }
        return index;
    }

    /**
     * 计算距离（相似性） 采用欧几里得算法
     *
     * @param p0
     * @param p1
     * @return
     */
    public double distanceValue(IndividualCommonPoint p0, IndividualCommonPoint p1) {
        double dis = 0;
        try {
            for (String column : needDisColumns) {
                IndividualCommonPoint userCommodityPair0 = p0;
                IndividualCommonPoint userCommodityPair1 = p1;
                Double p1Value = userCommodityPair0.get(column);
                Double p2Value = userCommodityPair1.get(column);
                dis += Math.pow(p1Value - p2Value, 2);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Math.sqrt(dis);
    }
}