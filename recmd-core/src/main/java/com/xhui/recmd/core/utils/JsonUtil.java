package com.xhui.recmd.core.utils;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * JsonUtil
 *
 * @author longlin(longlin@cyou-inc.com)
 * @date 2013-11-29
 * @since V1.0
 */
public class JsonUtil {
    private static final Logger log = Logger.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    
    /**
     * 将对象转化为Json格式字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            if (obj == null) {
                return "";
            }
            return mapper.writeValueAsString(obj);
        } catch (Exception ex) {
            log.error("toJson error：" + obj.getClass().getName(), ex);
            return "{}";
        }
    }

    /**
     * 将Json格式字符串转化为对象
     *
     * @param <T>
     * @param json
     * @param requiredType
     * @return
     */
    public static <T> T toObject(String json, Class<T> requiredType) {
        try {
            return mapper.readValue(json, requiredType);
        } catch (Exception ex) {
            log.error("toObject error：" + json + " >> " + requiredType.getName(), ex);
            return null;
        }
    }

    public static <T> T toObject(String json, TypeReference<T> requiredType) {
        try {
            return mapper.readValue(json, requiredType);
        } catch (Exception ex) {
            log.error("toObject error：" + json + " >> " + requiredType.getType(), ex);
            return null;
        }
    }
}
