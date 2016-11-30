package com.xhui.recmd.spark.job;

import com.xhui.recmd.spark.services.VisitCommodityMatcher;
import com.xhui.recmd.spark.services.VisitIpMather;
import com.xhui.recmd.spark.services.VisitTimeMather;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by littlehui on 2016/9/9.
 */
public class TestPattenMain {
    private final static String VISIT_PRIFIX = "http://mai.17173.com/commodity/visual/detail.html?commodityCode=";

    public static void main1(String[] args) {
        //String s = "\"1471708768.805 \\\"10.59.107.47\\\" 604 \\\"GET\\\" \\\"mai.17173.com\\\" \\\"/commodity/visual/detail.html?commodityCode=172031495257&type=v\\\" \\\"app\\\" \\\"10.59.112.175\\\" 80 \\\"/commodity/visual/detail.html\\\" \\\"commodityCode=172031495257&type=v\\\" \\\"HTTP/1.1\\\" 4114 3751 200 0.025 \\\"http://mai.17173.com/commodity/visual/detail.html?commodityCode=172031495257&type=v\\\" \\\"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1; 360Spider(compatible; HaosouSpider; http://www.haosou.com/help/help_3_2.html)\\\" \\\"-\\\" \\\"-\\\" \\\"751be66b65e2a8ef57b87e6007899e87\\\" \\\"0.1021.1021.1021.1021.1021.1021.1021\\\" \\\"127.0.0.1:8700\\\" \\\"101.226.168.239\\\" [20/Aug/2016:23:59:28 +0800]\\n\";";
        String s = "1471708768.805 \"10.59.107.47\" 604 \"GET\" \"mai.17173.com\" \"/commodity/visual/detail.html?commodityCode=172031495257&type=v\" \"app\" \"10.59.112.175\" 80 \"/commodity/visual/detail.html\" \"commodityCode=172031495257&type=v\" \"HTTP/1.1\" 4114 3751 200 0.025 \"http://mai.17173.com/commodity/visual/detail.html?commodityCode=172031495257&type=v\" \"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1; 360Spider(compatible; HaosouSpider; http://www.haosou.com/help/help_3_2.html)\" \"-\" \"-\" \"751be66b65e2a8ef57b87e6007899e87\" \"0.1021.1021.1021.1021.1021.1021.1021\" \"127.0.0.1:8700\" \"101.226.168.239\" [20/Aug/2016:23:59:28 +0800]\n";
        String s1 = "1474283747.506 \"10.59.67.32\" 788 \"GET\" \"mai.17173.com\" \"/front/shoppingCart/getCount.do?_=1474283748495\" \"app\" \"10.59.112.175\" 80 \"/front/shoppingCart/getCount.do\" \"_=1474283748495\" \"HTTP/1.1\" 370 101 200 0.008 \"http://mai.17173.com/commodity/materil/detail.html?commodityCode=181000000485&type=m\" \"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)\" \"1474284071276464\" \"-\" \"3a1666027734a3eb57dfc8e349697272\" \"0.43173\" \"127.0.0.1:8700\" \"119.52.163.235\" [19/Sep/2016:19:15:47 +0800]\n";
            //提取访问的commodityCode
        Pattern pattern = Pattern.compile("http://mai\\.17173\\.com/commodity/visual/detail\\.html\\?commodityCode=\\d{12}");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            String commodityPattenResult = matcher.group();
            String commodityCode = commodityPattenResult.substring(VISIT_PRIFIX.length(), commodityPattenResult.length());
            System.out.println("正则匹配的字符串：" + commodityPattenResult);
            System.out.println("商品编码:" + commodityCode);
        }
        //"111.161.98.20" [18/Aug/2016:23:59:29 +0800]
        Pattern patter = Pattern.compile("\"\\d+\\.\\d+\\.\\d+\\.\\d+\" \\[\\d+/.+]");
        Matcher matcherCommodity = patter.matcher(s);
        if (matcherCommodity.find()) {
            String commodityIP = matcherCommodity.group();
            String realIP = commodityIP.substring(1, commodityIP.indexOf("\" "));
            System.out.println("IP:" + realIP);
            String realTime = commodityIP.substring(commodityIP.indexOf("[") + 1, commodityIP.length() - 7);
            System.out.println("时间：" + realTime);
        }

        VisitCommodityMatcher visitCommoityMatcher = new VisitCommodityMatcher("http://mai\\.17173\\.com/commodity/visual/detail\\.html\\?commodityCode=\\d{12}");
        String commodityCode = visitCommoityMatcher.mather(s);
        if (commodityCode != null && !"".equals(commodityCode)) {
            //keyPair.setCode(commodityCode);
            System.out.println("商品编码:" + commodityCode);

            VisitIpMather visitIpMather = new VisitIpMather("\"\\d+\\.\\d+\\.\\d+\\.\\d+\" \\[\\d+/.+]");
            String ip = visitIpMather.mather(s);
            System.out.println("IP:" + ip);

            VisitTimeMather visitTimeMather = new VisitTimeMather("\\[\\d+/.+]");
            String time = visitTimeMather.mather(s);
            System.out.println("时间：" + time);
            //keyPair.setIp(ip);
        }
    }

    public static void main(String[] args) {
        //String s = "\"1471708768.805 \\\"10.59.107.47\\\" 604 \\\"GET\\\" \\\"mai.17173.com\\\" \\\"/commodity/visual/detail.html?commodityCode=172031495257&type=v\\\" \\\"app\\\" \\\"10.59.112.175\\\" 80 \\\"/commodity/visual/detail.html\\\" \\\"commodityCode=172031495257&type=v\\\" \\\"HTTP/1.1\\\" 4114 3751 200 0.025 \\\"http://mai.17173.com/commodity/visual/detail.html?commodityCode=172031495257&type=v\\\" \\\"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1; 360Spider(compatible; HaosouSpider; http://www.haosou.com/help/help_3_2.html)\\\" \\\"-\\\" \\\"-\\\" \\\"751be66b65e2a8ef57b87e6007899e87\\\" \\\"0.1021.1021.1021.1021.1021.1021.1021\\\" \\\"127.0.0.1:8700\\\" \\\"101.226.168.239\\\" [20/Aug/2016:23:59:28 +0800]\\n\";";
        String s1 = "1474283747.506 \"10.59.67.32\" 788 \"GET\" \"mai.17173.com\" \"/front/shoppingCart/getCount.do?_=1474283748495\" \"app\" \"10.59.112.175\" 80 \"/front/shoppingCart/getCount.do\" \"_=1474283748495\" \"HTTP/1.1\" 370 101 200 0.008 \"http://mai.17173.com/commodity/materil/detail.html?commodityCode=181000000485&type=m\" \"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)\" \"1474284071276464\" \"-\" \"3a1666027734a3eb57dfc8e349697272\" \"0.43173\" \"127.0.0.1:8700\" \"119.52.163.235\" [19/Sep/2016:19:15:47 +0800]\n";
        //提取访问的commodityCode
        Pattern pattern = Pattern.compile("http://mai\\.17173\\.com/front/order/visual/create\\.do\\?.*&commodityCode=\\d{12}");
        Matcher matcher = pattern.matcher(s1);
        if (matcher.find()) {
            String commodityPattenResult = matcher.group();
            int startIndex = commodityPattenResult.indexOf("commodityCode");
            String commodityCode = commodityPattenResult.substring(VISIT_PRIFIX.length(), commodityPattenResult.length());
            System.out.println("正则匹配的字符串：" + commodityPattenResult);
            System.out.println("商品编码:" + commodityCode);
        }
        //"111.161.98.20" [18/Aug/2016:23:59:29 +0800]
        Pattern patter = Pattern.compile("\"\\d+\\.\\d+\\.\\d+\\.\\d+\" \\[\\d+/.+]");
        Matcher matcherCommodity = patter.matcher(s1);
        if (matcherCommodity.find()) {
            String commodityIP = matcherCommodity.group();
            String realIP = commodityIP.substring(1, commodityIP.indexOf("\" "));
            System.out.println("IP:" + realIP);
            String realTime = commodityIP.substring(commodityIP.indexOf("[") + 1, commodityIP.length() - 7);
            System.out.println("时间：" + realTime);
        }

        VisitCommodityMatcher visitCommoityMatcher = new VisitCommodityMatcher("http://mai\\.17173\\.com/commodity/materil/detail\\.html\\?commodityCode=\\d{12}");
        String commodityCode = visitCommoityMatcher.mather(s1);
        if (commodityCode != null && !"".equals(commodityCode)) {
            //keyPair.setCode(commodityCode);
            System.out.println("商品编码:" + commodityCode);

            VisitIpMather visitIpMather = new VisitIpMather("\"\\d+\\.\\d+\\.\\d+\\.\\d+\" \\[\\d+/.+]");
            String ip = visitIpMather.mather(s1);
            System.out.println("IP:" + ip);

            VisitTimeMather visitTimeMather = new VisitTimeMather("\\[\\d+/.+]");
            String time = visitTimeMather.mather(s1);
            System.out.println("时间：" + time);
            //keyPair.setIp(ip);
        }
    }
}
