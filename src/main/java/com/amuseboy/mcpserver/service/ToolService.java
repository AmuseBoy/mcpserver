package com.amuseboy.mcpserver.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ToolService
 * @Description: TODO
 * @Author: liupeizhen
 * @Date: 2026/4/22 18:06
 */
@Service
public class ToolService {

    public Map<String, Object> handleText(String action, String input) {
        Map<String, Object> result = new HashMap<>();
        result.put("action", action);
        result.put("input", input);

        switch (action) {
            case "reverse" -> result.put("output", new StringBuilder(input).reverse().toString());
            case "uppercase" -> result.put("output", input.toUpperCase());
            case "lowercase" -> result.put("output", input.toLowerCase());
            case "length" -> result.put("output", input.length());
            case "word_count" -> result.put("output", input.trim().split("\\s+").length);
            default -> {
                result.put("error", "Unknown action: " + action);
                result.put("available_actions", new String[]{"reverse", "uppercase", "lowercase", "length", "word_count"});
            }
        }
        return result;
    }

    public Map<String, Object> handleMath(String action, double a, double b) {
        Map<String, Object> result = new HashMap<>();
        result.put("action", action);
        result.put("a", a);
        result.put("b", b);

        switch (action) {
            case "add" -> result.put("output", a + b);
            case "subtract" -> result.put("output", a - b);
            case "multiply" -> result.put("output", a * b);
            case "divide" -> {
                if (b == 0) result.put("error", "Division by zero");
                else result.put("output", a / b);
            }
            default -> {
                result.put("error", "Unknown action: " + action);
                result.put("available_actions", new String[]{"add", "subtract", "multiply", "divide"});
            }
        }
        return result;
    }


}
