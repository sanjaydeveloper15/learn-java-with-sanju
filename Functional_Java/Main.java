// Lesson 6: Functional Java — Lambdas, Streams, Optional
// Java 8+ brought functional programming features.
// Coming from JS/Python: lambdas = arrow functions, streams = array methods (.map/.filter/.reduce)

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

// ─── 1. Lambdas ───────────────────────────────────────────────────────────────
// Like JS arrow functions or Python lambdas.
// Syntax: (params) -> expression  OR  (params) -> { body; }

interface Greeter {
    String greet(String name);  // functional interface — exactly ONE abstract method
}

class LambdaDemo {
    static void run() {
        // Old way — anonymous class
        Greeter formal = new Greeter() {
            public String greet(String name) { return "Good day, " + name; }
        };

        // Lambda way — like JS: (name) => "Hello, " + name
        Greeter casual = name -> "Hey, " + name;

        System.out.println(formal.greet("Alice"));  // Good day, Alice
        System.out.println(casual.greet("Alice"));  // Hey, Alice

        // Lambda with block body
        Greeter shout = name -> {
            String upper = name.toUpperCase();
            return "HEY " + upper + "!";
        };
        System.out.println(shout.greet("Alice"));   // HEY ALICE!
    }
}

// ─── 2. Built-in Functional Interfaces ────────────────────────────────────────
// Java ships common functional interfaces in java.util.function:
//   Function<T,R>  — takes T, returns R       (like JS map callback)
//   Predicate<T>   — takes T, returns boolean  (like JS filter callback)
//   Consumer<T>    — takes T, returns nothing  (like JS forEach callback)
//   Supplier<T>    — takes nothing, returns T  (like a factory function)

class FunctionalInterfaceDemo {
    static void run() {
        Function<String, Integer> strLen = s -> s.length();
        System.out.println(strLen.apply("hello"));  // 5

        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println(isEven.test(4));   // true
        System.out.println(isEven.test(7));   // false

        Consumer<String> printer = s -> System.out.println(">> " + s);
        printer.accept("hello");              // >> hello

        Supplier<String> greeting = () -> "Good morning!";
        System.out.println(greeting.get());   // Good morning!
    }
}

// ─── 3. Streams ───────────────────────────────────────────────────────────────
// Like JS array methods chained: .filter().map().reduce()
// Streams don't modify the original list — they produce a new result.

class StreamDemo {
    static void run() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter — like JS .filter()
        List<Integer> evens = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Evens: " + evens);  // [2, 4, 6, 8, 10]

        // map — like JS .map()
        List<Integer> doubled = numbers.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("Doubled: " + doubled);  // [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]

        // filter + map chained
        List<String> result = numbers.stream()
            .filter(n -> n > 5)
            .map(n -> "item-" + n)
            .collect(Collectors.toList());
        System.out.println("Mapped: " + result);  // [item-6, item-7, ...]

        // reduce — like JS .reduce()
        int sum = numbers.stream()
            .reduce(0, (acc, n) -> acc + n);
        System.out.println("Sum: " + sum);  // 55

        // forEach — like JS .forEach()
        numbers.stream()
            .filter(n -> n % 3 == 0)
            .forEach(n -> System.out.print(n + " "));  // 3 6 9
        System.out.println();

        // count
        long count = numbers.stream()
            .filter(n -> n > 5)
            .count();
        System.out.println("Count > 5: " + count);  // 5
    }
}

// ─── 4. Optional ──────────────────────────────────────────────────────────────
// Wrapper to handle null safely — like JS optional chaining (?.) or Python's None checks.
// Avoids NullPointerException.

class OptionalDemo {
    static Optional<String> findUser(boolean exists) {
        return exists ? Optional.of("Sanjay") : Optional.empty();
    }

    static void run() {
        Optional<String> user = findUser(true);

        // isPresent / get
        if (user.isPresent()) {
            System.out.println("Found: " + user.get());  // Found: Sanjay
        }

        // orElse — like JS: value ?? "default"
        String name = findUser(false).orElse("Guest");
        System.out.println("User: " + name);  // User: Guest

        // orElseGet — lazy default (runs only if empty)
        String name2 = findUser(false).orElseGet(() -> "Generated_" + System.currentTimeMillis());
        System.out.println("User: " + name2);

        // ifPresent — like JS: value?.doSomething()
        findUser(true).ifPresent(u -> System.out.println("Hello, " + u));  // Hello, Sanjay

        // map — transform if present
        int length = findUser(true)
            .map(u -> u.length())
            .orElse(0);
        System.out.println("Name length: " + length);  // 6
    }
}

// ─── Main — run everything ────────────────────────────────────────────────────

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Lambdas ===");
        LambdaDemo.run();

        System.out.println("\n=== Functional Interfaces ===");
        FunctionalInterfaceDemo.run();

        System.out.println("\n=== Streams ===");
        StreamDemo.run();

        System.out.println("\n=== Optional ===");
        OptionalDemo.run();
    }
}
