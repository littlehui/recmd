package com.xhui.recmd.spark.services;

import java.util.regex.Matcher;

/**
 * Created by littlehui on 2016/11/2.
 */
public class MaterialCommodityMather extends AbstractVisitMather {

    private final static String VISIT_PRIFIX = "http://mai.17173.com/commodity/materil/detail.html?commodityCode=";

    public MaterialCommodityMather(String regrex) {
        super(regrex);
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
