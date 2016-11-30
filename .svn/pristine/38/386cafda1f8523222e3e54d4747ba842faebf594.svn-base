package com.xhui.recmd.spark.services;

import java.util.regex.Matcher;

/**
 * Created by littlehui on 2016/9/22.
 */
public class OrderCommodityMatcher extends AbstractVisitMather {

    private final static String COMMODITY_ORDER_PREFIX = "http://mai.17173.com/commodity/visual/orderstep.html?commodityCode=";

    public OrderCommodityMatcher(String regex) {
        super(regex);
    }

    @Override
    public String mather(String url) {
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            String commodityPattenResult = matcher.group();
            String commodityCode = commodityPattenResult.substring(COMMODITY_ORDER_PREFIX.length(), commodityPattenResult.length());
            // System.out.println("正则匹配的字符串：" + commodityPattenResult);
            // System.out.println("商品编码:" + commodityCode);
            return commodityCode;
        }
        return null;
    }
}
