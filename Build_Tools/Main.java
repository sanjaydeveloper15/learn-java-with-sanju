// Lesson 8: Build Tools — Maven & Gradle
//
// Coming from Node.js/PHP/Python:
//   Maven/Gradle  =  npm / composer / pip
//   pom.xml       =  package.json
//   build.gradle  =  package.json (Groovy/Kotlin DSL)
//   ~/.m2/        =  node_modules (but global, shared across projects)
//
// See pom.xml and build.gradle in this folder for real config examples.
//
// ─── Maven ────────────────────────────────────────────────────────────────────
//
// Most common commands:
//
//   mvn compile          — compile source code  (like tsc)
//   mvn test             — run tests
//   mvn package          — compile + test + create JAR file
//   mvn install          — package + install JAR to local ~/.m2 cache
//   mvn clean            — delete /target (build output)
//   mvn clean package    — clean then build (most common combo)
//   mvn dependency:tree  — show all dependencies (like npm ls)
//
// ─── Gradle ───────────────────────────────────────────────────────────────────
//
// Gradle is faster than Maven (incremental builds, daemon).
// Uses Groovy or Kotlin DSL instead of XML.
//
//   ./gradlew build        — compile + test + package
//   ./gradlew test         — run tests
//   ./gradlew clean        — delete /build output
//   ./gradlew dependencies — show dependency tree
//   ./gradlew bootRun      — run Spring Boot app (Spring specific)
//
// ─── Maven vs Gradle ──────────────────────────────────────────────────────────
//
//   Maven   — XML config, verbose but predictable. Industry standard for years.
//   Gradle  — DSL config, concise and faster. Default for Android + Spring Boot.
//
//   Spring Boot uses Gradle by default (when generated via start.spring.io).
//
// ─── Dependency scope ─────────────────────────────────────────────────────────
//
// Maven scopes (like npm dependencies vs devDependencies):
//
//   compile   — needed at compile + runtime (default)    = npm dependency
//   test      — only for tests, not in final JAR         = npm devDependency
//   provided  — compile only, provided at runtime (e.g. servlet container)
//   runtime   — not needed to compile, needed to run (e.g. DB driver)
//
// ─── Project structure (Maven standard) ──────────────────────────────────────
//
//   my-app/
//   ├── pom.xml                     ← dependency + build config
//   ├── src/
//   │   ├── main/
//   │   │   └── java/com/example/   ← your application code
//   │   └── test/
//   │       └── java/com/example/   ← your test code
//   └── target/                     ← compiled output (auto-generated, git-ignored)

public class Main {
    public static void main(String[] args) {
        System.out.println("Build Tools — see pom.xml and build.gradle in this folder");
        System.out.println("Maven commands  : mvn clean package | mvn test | mvn dependency:tree");
        System.out.println("Gradle commands : ./gradlew build   | ./gradlew test | ./gradlew dependencies");
    }
}
