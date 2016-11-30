package com.xhui.recmd.core.union;

/**
 * Created by littlehui on 2016/10/11.
 */
public class ColumnGoal implements Comparable<ColumnGoal> {

    private String column;

    private double goal;

    public ColumnGoal(String column, double goal) {
        this.column = column;
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "ColumnGoal{" +
                "column='" + column + '\'' +
                ", goal=" + goal +
                '}';
    }

    public ColumnGoal() {

    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColumnGoal that = (ColumnGoal) o;

        if (Double.compare(that.goal, goal) != 0) return false;
        return !(column != null ? !column.equals(that.column) : that.column != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = column != null ? column.hashCode() : 0;
        temp = Double.doubleToLongBits(goal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(ColumnGoal o) {
        if (o != null) {
            if (this.equals(o)) {
                return 0;
            } else {
                try {
                    return this.getColumn().compareTo(o.getColumn());
                } catch (Exception e) {
                    return this.getGoal() - o.getGoal() < 0 ? -1 : 1;
                }
            }
        }
        return 1;
    }
}
