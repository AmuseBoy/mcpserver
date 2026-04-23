package com.amuseboy.mcpserver.web;

import com.amuseboy.mcpserver.service.ToolService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName: ToolController
 * @Description: TODO
 * @Author: liupeizhen
 * @Date: 2026/4/22 18:01
 */
@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    /**
     * 字符串工具：反转、统计字数等
     * 示例：POST /api/tools/text
     * Body: {"action": "reverse", "input": "hello"}
     */
    @PostMapping("/text")
    public Map<String, Object> textTool(@RequestBody Map<String, String> request) {
        String action = request.get("action");
        String input = request.get("input");
        return toolService.handleText(action, input);
    }

    /**
     * 数学计算
     * 示例：POST /api/tools/math
     * Body: {"action": "add", "a": "10", "b": "20"}
     */
    @PostMapping("/math")
    public Map<String, Object> mathTool(@RequestBody Map<String, String> request) {
        String action = request.get("action");
        double a = Double.parseDouble(request.get("a"));
        double b = Double.parseDouble(request.get("b"));
        return toolService.handleMath(action, a, b);
    }

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok", "service", "demo-api");
    }
}
