package com.xhui.recmd.core.union;

/**
 * Created by littlehui on 2016/9/11 0011.
 */

import java.util.ArrayList;
import java.util.List;
public class TestMain {

    public static void main(String[] args) {
        List<IndividualCommonPoint> individualInterestPoints =new ArrayList<IndividualCommonPoint>();

        IndividualCommonPoint p1 = new IndividualCommonPoint();
        p1.setIp("10.5.32.97");
        p1.addColumnGoal(new ColumnGoal("391000000067", 1.2d));
        p1.addColumnGoal(new ColumnGoal("391000000367", 5.2d));
        p1.addColumnGoal(new ColumnGoal("391000000267", 1.2d));
        p1.addColumnGoal(new ColumnGoal("391000003467", 0.2d));

        IndividualCommonPoint p2 = new IndividualCommonPoint();
        p2.setIp("10.5.32.98");
        p2.addColumnGoal(new ColumnGoal("391000000067", 1.2d));
        p2.addColumnGoal(new ColumnGoal("391000000367", 5.2d));
        p2.addColumnGoal(new ColumnGoal("391000000267", 4.2d));
        p2.addColumnGoal(new ColumnGoal("391000003467", 0.2d));

        IndividualCommonPoint p3 = new IndividualCommonPoint();
        p3.setIp("10.5.32.99");
        p3.addColumnGoal(new ColumnGoal("391000000067", 1.2d));
        p3.addColumnGoal(new ColumnGoal("391000000367", 0.2d));
        p3.addColumnGoal(new ColumnGoal("391000000267", 0.2d));
        p3.addColumnGoal(new ColumnGoal("391000003467", 2.1d));

        IndividualCommonPoint p4 = new IndividualCommonPoint();
        p4.setIp("10.5.32.100");
        p4.addColumnGoal(new ColumnGoal("391000000067", 1.2d));
        p4.addColumnGoal(new ColumnGoal("391000000367", 0.2d));
        p4.addColumnGoal(new ColumnGoal("391000000267", 0.2d));
        p4.addColumnGoal(new ColumnGoal("391000003467", 2.3d));

        IndividualCommonPoint p5 = new IndividualCommonPoint();
        p5.setIp("10.5.32.101");
        p5.addColumnGoal(new ColumnGoal("391000000067", 1.2d));
        p5.addColumnGoal(new ColumnGoal("391000000367", 5.2d));
        p5.addColumnGoal(new ColumnGoal("391000000267", 0.2d));
        p5.addColumnGoal(new ColumnGoal("391000003467", 0.3d));

        IndividualCommonPoint p6 = new IndividualCommonPoint();
        p6.setIp("10.5.32.102");
        p6.addColumnGoal(new ColumnGoal("391000000067", 1.2d));
        p6.addColumnGoal(new ColumnGoal("391000000367", 1.2d));
        p6.addColumnGoal(new ColumnGoal("391000000267", 1.2d));
        p6.addColumnGoal(new ColumnGoal("391000003467", 1.3d));

        IndividualCommonPoint p7 = new IndividualCommonPoint();
        p7.setIp("10.5.32.103");
        p7.addColumnGoal(new ColumnGoal("391000000067", 0.1d));
        p7.addColumnGoal(new ColumnGoal("391000000367", 8.2d));

        individualInterestPoints.add(p1);
        individualInterestPoints.add(p2);
        individualInterestPoints.add(p3);
        individualInterestPoints.add(p4);
        individualInterestPoints.add(p5);
        individualInterestPoints.add(p6);
        individualInterestPoints.add(p7);

        KMeansCluster kMeansCluster = new KMeansCluster(individualInterestPoints,4);
        List<IndividualCommonPoint>[] results = kMeansCluster.cluster();
        ArraySet<IndividualCommonPoint> arraySet = kMeansCluster.getInitIndividual();
        for (int i = 0; i < results.length; i++) {
            System.out.println("==================中心" + (i + 1) + "================");
            System.out.println(arraySet.get(i).toString());
            System.out.println("===========类别" + (i + 1) + "================");
            List<IndividualCommonPoint> list = results[i];
            for (IndividualCommonPoint p : list) {
                System.out.println(p.getIp() + "与中心的距离：" + p.getClassCoreDis() + "--->"
                        + "," + p.getColumnGoals());
            }
        }
    }
}  