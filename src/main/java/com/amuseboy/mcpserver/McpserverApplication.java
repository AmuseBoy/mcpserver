package com.amuseboy.mcpserver;

import com.amuseboy.mcpserver.service.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpserverApplication.class, args);
    }

}
