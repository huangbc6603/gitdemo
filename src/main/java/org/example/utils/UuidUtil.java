package org.example.utils;

import java.util.UUID;

/**
 * @author Derek-huang
 */
public class UuidUtil {

    /**
     * 获取long类型的
     *
     * @return
     */
    public static long getLongId() {
        return snowflake.getId();
    }

    /**
     * 指定datacenterId支持分布式
     */
    private static SnowflakeIDGenerator snowflake = new SnowflakeIDGenerator(0);

    /**
     * 获取Long形式的字符串UUID，例如：873832348837326848 Generate unique IDs using the
     * Twitter Snowflake algorithm (see https://github.com/twitter/snowflake).
     * Snowflake IDs are 64 bit positive longs composed of: - 41 bits time stamp
     * - 10 bits machine id - 12 bits sequence number
     *
     * @return Return the next unique id for the type with the given name using
     * the generator's id generation strategy
     */
    public static String getLongUUID() {
        return String.valueOf(getLongId());
    }

    /**
     * 生成全局的字符串UUID
     *
     * @return
     */
    public static String getStringUUID() {
        return UUID.randomUUID().toString();
    }
}
