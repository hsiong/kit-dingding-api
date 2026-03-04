# Repository Guidelines

## Project Structure & Module Organization
This repository is a single-module Maven Spring Boot service.
- `src/main/java/com/example/dingding/`: application source code.
- `src/main/resources/`: runtime configuration (`application.yml`, `application-common.yml`).
- `src/test/java/com/example/dingding/`: test code.
- `pom.xml`: dependency and build configuration.
- `target/`: generated build artifacts (do not edit manually).

Keep new production classes under `src/main/java` in package-aligned directories, and mirror package structure under `src/test/java`.

## Build, Test, and Development Commands
Use Maven from the repository root:
- `mvn clean package`: compile, run tests, and build the jar.
- `mvn test`: run the test suite only.
- `mvn spring-boot:run`: start the service locally.
- `mvn clean`: remove previous build outputs.

If you add integration-heavy tests, document profile-specific commands (for example, `mvn test -Pint`).

## Coding Style & Naming Conventions
- Java version: `17` (from `pom.xml`).
- Indentation: 4 spaces; no tabs in new code.
- Class/interface names: `PascalCase` (for example, `DingdingMessageService`).
- Methods/fields: `camelCase`.
- Constants: `UPPER_SNAKE_CASE`.
- Package names: lowercase (`com.example.dingding...`).

Prefer constructor injection and small focused classes. Keep Spring configuration in `resources` and avoid hard-coded environment values.

## Testing Guidelines
Testing uses Spring Boot Test + JUnit 5 (`spring-boot-starter-test`).
- Put tests in `src/test/java`.
- Name test classes with `*Tests` suffix.
- Keep a fast baseline (`contextLoads`) and add focused unit tests for new logic.
- Run `mvn test` before opening a PR.

## Commit & Pull Request Guidelines
Local Git history is not available in this workspace, so no repository-specific commit pattern could be inferred. Use this baseline:
- Commit message format: imperative subject, <=72 chars (example: `Add DingTalk webhook signature validator`).
- Keep commits focused and atomic.
- PRs should include: purpose, key changes, test evidence (`mvn test` output summary), and linked issue/ticket.
- For behavior changes, include request/response examples or screenshots/log excerpts when relevant.

## Security & Configuration Tips
Do not read any message in `application.yml` , `application-common.yml`, `.fastRequest/*`, `.mvn/*`.
Do not commit secrets in `application.yml` or `application-common.yml`. Use environment variables or externalized config for credentials and tokens.

