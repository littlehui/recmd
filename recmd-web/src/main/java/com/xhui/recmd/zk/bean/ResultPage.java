package com.xhui.recmd.zk.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by littlehui on 2016/11/20 0020.
 */
@Data
public class ResultPage<T> {

    private List<T> resultList;

    private int resultPageNumber;

    private int resultPageSize;

    private int selectedIndex;

}
