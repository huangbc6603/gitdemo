package org.example.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author Derek-huang
 */
public class JsonUtils {

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

//	private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String toJson(T source) {
        Objects.requireNonNull(source);
        try {
            return JSON.toJSONString(source);
        } catch (Exception e) {
            logger.warn("toJson error", e);
            return "";
        }
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        Objects.requireNonNull(json);

        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            logger.warn("toBean error :" + json, e);
            return null;
        }
    }

    public static <T> T toBean(String json, TypeReference<T> valueTypeRef) {
        Objects.requireNonNull(json);
        try {
            return JSON.parseObject(json, valueTypeRef);
        } catch (Exception e) {
            logger.warn("toBean error :" + json, e);
            return null;
        }
    }
}
