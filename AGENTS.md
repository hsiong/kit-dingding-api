# Repository Guidelines

## Project Structure & Module Organization
This repository is a single-module Maven Spring Boot service.
- `src/main/java/com/example/dingding/`: application source code.
- `src/main/resources/`: runtime configuration (`application.yml`, `application-common.yml`).
- `src/test/java/com/example/dingding/`: test code.
- `pom.xml`: dependency and build configuration.
- `target/`: generated build artifacts (do not edit manually).

Keep new production classes under `src/main/java` in package-aligned directories, and mirror package structure under `src/test/java`.


## Coding Style & Naming Conventions
- Java version: `17` (from `pom.xml`).
- Indentation: 4 spaces; no tabs in new code.
- Class/interface names: `PascalCase` (for example, `DingdingMessageService`).
- Methods/fields: `camelCase`.
- Constants: `UPPER_SNAKE_CASE`.
- Package names: lowercase (`com.example.dingding...`).

Prefer constructor injection and small focused classes. Keep Spring configuration in `resources` and avoid hard-coded environment values.

## Testing Guidelines
There is no need to run any test

## Security & Configuration Tips
Do not read any message in `application.yml` , `application-common.yml`, `.fastRequest/*`, `.mvn/*`.
Do not commit secrets in `application.yml` or `application-common.yml`. Use environment variables or externalized config for credentials and tokens.

