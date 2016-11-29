package com.xhui.recmd.spark.services;

import java.util.regex.Matcher;

/**
 * Created by littlehui on 2016/9/12.
 */
public class VisitTimeMather extends AbstractVisitMather {

    public VisitTimeMather(String regex) {
        super(regex);
    }

    @Override
    public String mather(String url) {
        Matcher matcherCommodity = pattern.matcher(url);
        if (matcherCommodity.find()) {
            String commodityIP = matcherCommodity.group();
            String realTime = commodityIP.substring(commodityIP.indexOf("[") + 1, commodityIP.length() - 7);
            //System.out.println("时间：" + realTime);
            return realTime;
        }
        return null;
    }
}
