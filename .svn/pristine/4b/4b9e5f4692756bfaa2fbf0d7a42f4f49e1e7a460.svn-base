package com.xhui.recmd.spark.services;

import java.util.regex.Matcher;

/**
 * Created by littlehui on 2016/9/12.
 */
public class VisitCommodityMatcher extends AbstractVisitMather {

    private final static String VISIT_PRIFIX = "http://mai.17173.com/commodity/visual/detail.html?commodityCode=";

    public VisitCommodityMatcher(String regex) {
        super(regex);
    }

    @Override
    public String mather(String url) {
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            String commodityPattenResult = matcher.group();
            String commodityCode = commodityPattenResult.substring(VISIT_PRIFIX.length(), commodityPattenResult.length());
            // System.out.println("正则匹配的字符串：" + commodityPattenResult);
            // System.out.println("商品编码:" + commodityCode);
            return commodityCode;
        }
        return null;
    }
}
