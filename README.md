# Learn Java with Sanju

A structured Java learning journey — from core concepts to Spring Boot — by a developer coming from PHP, Node.js, and Python background.

---

## How to Run Java Files

Java is a compiled language (similar to TypeScript → JavaScript).

**Step 1: Compile** (generates a `.class` bytecode file)
```bash
javac test.java
```

**Step 2: Run**
```bash
java test
```

> Every time you change the source file, re-run `javac` to recompile before running.

---

## Learning Roadmap

### Phase 1 — Java Core
- [x] Lesson 1: Basics — types, strings, operators, loops, arrays → [Basics/test.java](Basics/test.java)
- [x] Lesson 2: OOP — classes, objects, constructors → [OOP/Main.java](OOP/Main.java)
- [x] Lesson 3: Interfaces & Abstract Classes → [Interfaces/Main.java](Interfaces/Main.java)
- [ ] Lesson 4: Collections & Generics
- [ ] Lesson 5: Exception Handling
- [ ] Lesson 6: Functional Java — Lambdas, Streams, Optional
- [ ] Lesson 7: Concurrency basics
- [ ] Lesson 8: Build tools — Maven / Gradle

### Phase 2 — Spring Boot
- [ ] Spring Core — IoC, Dependency Injection, Beans
- [ ] Spring Boot setup & auto-configuration
- [ ] REST APIs with Spring MVC
- [ ] Spring Data JPA + Hibernate
- [ ] Spring Security
- [ ] Testing — JUnit, Mockito

---

## Key Concepts (Quick Reference)

| Concept | Java | Node.js / PHP / Python |
|---|---|---|
| Compile step | `javac file.java` | Not needed (interpreted) |
| Run | `java ClassName` | `node file.js` / `php file.php` / `python file.py` |
| Types | Strictly typed | Dynamic |
| String compare | `.equals()` | `==` |
| Integer division | `10 / 3 = 3` | Usually returns float |
| Array size | Fixed at creation | Dynamic |
| Package manager | Maven / Gradle | npm / composer / pip |
