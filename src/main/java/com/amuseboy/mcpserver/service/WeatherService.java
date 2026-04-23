package com.amuseboy.mcpserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class WeatherService {

    @Tool(description = "根据城市名称获取天气预报，支持：西安、北京、上海")
    public String getWeatherByCity(
            @ToolParam(description = "城市名称，例如：北京") String city) {
        log.info("查询城市天气: {}", city);
        Map<String, String> mockData = Map.of(
                "西安", "晴天",
                "北京", "小雨",
                "上海", "大雨");
        return mockData.getOrDefault(city, "抱歉：未查询到对应的城市天气信息");
    }

}
