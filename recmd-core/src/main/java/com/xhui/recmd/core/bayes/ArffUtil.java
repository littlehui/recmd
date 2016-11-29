package com.xhui.recmd.core.bayes;

import java.util.List;

/**
 * Created by littlehui on 2016/10/19.
 */
public class ArffUtil {
    public int getValueIndex(String decAttributeName, List<String> attributeList) {
        int index = 0;
        if (attributeList != null && attributeList.size() > 0) {
            for (String attribute : attributeList) {
                if (attribute.equals(decAttributeName)) {
                    return index;
                }
                index++;
            }
        }
        return index;
    }
}
