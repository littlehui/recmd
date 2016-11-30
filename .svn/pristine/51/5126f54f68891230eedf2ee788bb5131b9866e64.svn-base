package com.xhui.recmd.core.union;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by littlehui on 2016/11/1.
 */
public class KMeansUtils {
    public static double distanceValue(IndividualCommonPoint p0, IndividualCommonPoint p1) {
        double dis = 0;
        Set<String> needDisColumns = new HashSet<>();
        needDisColumns.addAll(p0.keySet());
        needDisColumns.addAll(p1.keySet());
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
