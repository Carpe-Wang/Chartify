package com.chartify.chartify.utils;

import org.springframework.stereotype.Component;


@Component
public class SnowflakeIdGenerator {
    // 这里是简化版的 ID 生成器，实际的生成器需要根据twitter-snowflake算法进行配置。
    private long lastTimestamp = -1L;

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        lastTimestamp = timestamp;

        // 这里的位移和最终的 ID 结合逻辑根据你的需求可能有所不同
        return ((timestamp - 1288834974657L) << 22) | (1L << 17) | (1L << 12) | (timestamp % 4096);
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}