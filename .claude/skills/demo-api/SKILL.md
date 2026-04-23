---
name: demo-api
description: >
  调用本地 SpringBoot Demo API 的基础能力接口。
  支持文本处理（reverse/uppercase/lowercase/length/word_count）
  和数学计算（add/subtract/multiply/divide）。
  当用户说"用 demo-api"、"调用 demo-api"、"帮我调用本地接口"时触发。
  用法示例：
    "用 demo-api 帮我把 'hello world' 转成大写"
    "用 demo-api 计算 123 乘以 456"
---

# Demo API Skill

## 概述

这个 Skill 通过 curl 调用本地运行的 SpringBoot Demo API（默认地址 http://localhost:8080）。

API 基础地址由环境变量 `DEMO_API_BASE` 控制，默认为 `http://localhost:8080`。

## 使用方法

### 文本处理

```bash
# 反转字符串
curl -s -X POST "${DEMO_API_BASE:-http://localhost:8080}/api/tools/text" \
  -H "Content-Type: application/json" \
  -d '{"action": "reverse", "input": "hello world"}'

# 转大写
curl -s -X POST "${DEMO_API_BASE:-http://localhost:8080}/api/tools/text" \
  -H "Content-Type: application/json" \
  -d '{"action": "uppercase", "input": "hello world"}'

# 统计字数
curl -s -X POST "${DEMO_API_BASE:-http://localhost:8080}/api/tools/text" \
  -H "Content-Type: application/json" \
  -d '{"action": "word_count", "input": "hello world foo bar"}'
```

支持的 action：`reverse` | `uppercase` | `lowercase` | `length` | `word_count`

### 数学计算

```bash
# 加法
curl -s -X POST "${DEMO_API_BASE:-http://localhost:8080}/api/tools/math" \
  -H "Content-Type: application/json" \
  -d '{"action": "add", "a": "10", "b": "20"}'

# 除法
curl -s -X POST "${DEMO_API_BASE:-http://localhost:8080}/api/tools/math" \
  -H "Content-Type: application/json" \
  -d '{"action": "divide", "a": "100", "b": "4"}'
```

支持的 action：`add` | `subtract` | `multiply` | `divide`

### 健康检查

```bash
curl -s "${DEMO_API_BASE:-http://localhost:8080}/api/tools/health"
```

## 注意事项

- 调用前请确认 SpringBoot 服务已启动（`mvn spring-boot:run`）
- 如果服务运行在不同端口，设置环境变量：`export DEMO_API_BASE=http://localhost:9090`
- 返回值为 JSON 格式，`output` 字段包含计算结果，`error` 字段包含错误信息
