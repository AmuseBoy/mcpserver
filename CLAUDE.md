# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

```bash
# Build (skip tests)
mvn clean package -DskipTests

# Run
mvn spring-boot:run

# Run tests
mvn test

# Run a single test class
mvn test -Dtest=McpserverApplicationTests
```

The server starts on `http://localhost:8080`.

## Architecture

This is a **Spring AI MCP Server** — it exposes tools to MCP clients (e.g., Claude Desktop, other AI agents) over HTTP using the **Streamable HTTP** transport protocol.

### Key components

- **`McpserverApplication`** — Spring Boot entry point.
- **`WeatherService`** — Declares MCP tools via `@Tool` / `@ToolParam` annotations (Spring AI). Currently provides a mock weather lookup for Xi'an, Beijing, and Shanghai.
- **`MyConfig`** — Registers `WeatherService` as a `ToolCallbackProvider` bean so Spring AI auto-exposes it over MCP.

### How tools are registered

1. Add `@Tool(description = "...")` to a method in a `@Service` class.
2. Register the service bean via `MethodToolCallbackProvider.builder().toolObjects(yourService).build()` in `MyConfig`.

No controller code is needed — `spring-ai-starter-mcp-server-webmvc` handles the MCP HTTP endpoint automatically at `/mcp`.

### MCP configuration (`application.yml`)

| Property | Value | Notes |
|---|---|---|
| `protocol` | `STREAMABLE` | Uses Streamable HTTP (MCP 2025-03-26 spec) |
| `type` | `SYNC` | Synchronous tool execution |
| `*-change-notification` | `false` | Tools/resources/prompts are static |

### MCP client config (`.mcp.json`)

Used by Claude Code / MCP clients to connect to this server locally:

```json
{
  "mcpServers": {
    "my-tool": {
      "type": "http",
      "url": "http://localhost:8080/mcp"
    }
  }
}
```

## Tech stack

- Java 17, Spring Boot 3.4.4, Spring AI 1.1.4
- Transport: `spring-ai-starter-mcp-server-webmvc` (Servlet stack, SSE + Streamable HTTP)
- Lombok for logging (`@Slf4j`)
