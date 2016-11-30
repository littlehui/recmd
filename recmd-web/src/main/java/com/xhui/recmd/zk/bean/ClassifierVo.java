package com.xhui.recmd.zk.bean;

import lombok.Data;

/**
 * Created by littlehui on 2016/11/20 0020.
 */
@Data
public class ClassifierVo {

    private String classifierKey;

    private String classifierIp;

    private Double classifierIpDis;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassifierVo that = (ClassifierVo) o;

        if (!classifierKey.equals(that.classifierKey)) return false;
        return classifierIp.equals(that.classifierIp);

    }

    @Override
    public int hashCode() {
        int result = classifierKey.hashCode();
        result = 31 * result + classifierIp.hashCode();
        return result;
    }
}
