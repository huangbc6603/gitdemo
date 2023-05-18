package org.example.utils;

import com.alibaba.fastjson.JSONObject;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: Derek.huang on 2023/5/18 11:32.
 */
public class P6spySqlFormatConfig implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        Map<String, Object> message = new LinkedHashMap<>(8);
        String newPrepared = prepared.replace("   ", "").replace("\n", " ");
        message.put("prepared", newPrepared);
        String newSql = sql.replace("   ", "").replace("\n", " ");
        message.put("sql", newSql);
        return JSONObject.toJSONString(message, true);
    }

}
