// Lesson 4: Collections & Generics
// Coming from Node.js/PHP/Python: Java has typed collections — no mixed-type arrays.
// The <Type> syntax is Generics — it locks what goes in the collection.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// ─── 1. ArrayList ─────────────────────────────────────────────────────────────
// Like JS Array or Python list — dynamic size, ordered, allows duplicates.
// ArrayList<Type> — the <Type> is the generic (replaces JS's any[])

class ListDemo {
    static void run() {
        List<String> names = new ArrayList<>();

        names.add("Alice");
        names.add("Sanjay");
        names.add("Bob");
        names.add("Alice");  // duplicates allowed

        System.out.println(names);           // [Alice, Bob, Alice]
        System.out.println(names.get(0));    // Alice  (like names[0] in JS/Python)
        System.out.println(names.size());    // 3      (like .length / len())

        names.remove("Bob");
        System.out.println(names);           // [Alice, Alice]

        // Loop — same as JS for...of / Python for
        for (String name : names) {
            System.out.println("Hello, " + name);
        }
    }
}

// ─── 2. HashMap ───────────────────────────────────────────────────────────────
// Like JS object/Map or PHP associative array or Python dict.
// HashMap<KeyType, ValueType>

class MapDemo {
    static void run() {
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Alice", 95);    // like scores["Alice"] = 95
        scores.put("Bob", 87);
        scores.put("Alice", 99);    // overwrites — keys are unique

        System.out.println(scores.get("Alice"));           // 99
        System.out.println(scores.getOrDefault("Eve", 0)); // 0 — safe default (like JS ?? or Python .get())
        System.out.println(scores.containsKey("Bob"));     // true

        scores.remove("Bob");

        // Loop over key-value pairs — like Object.entries() in JS
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

// ─── 3. HashSet ───────────────────────────────────────────────────────────────
// Like Python set or JS Set — unordered, NO duplicates.
// Useful for uniqueness checks.

class SetDemo {
    static void run() {
        Set<String> tags = new HashSet<>();

        tags.add("java");
        tags.add("backend");
        tags.add("java");    // ignored — already exists

        System.out.println(tags);                  // [backend, java] (order not guaranteed)
        System.out.println(tags.contains("java")); // true
        System.out.println(tags.size());           // 2
    }
}

// ─── 4. Generics — writing your own ──────────────────────────────────────────
// <T> is a type placeholder — like TypeScript generics.
// Lets you write one class/method that works for any type, safely.

class Box<T> {
    private T value;

    Box(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }

    void describe() {
        System.out.println("Box contains: " + value + " (" + value.getClass().getSimpleName() + ")");
    }
}

// Generic method — works for any type without a generic class
class Utils {
    static <T> void printArray(T[] arr) {
        for (T item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}

// ─── Main — run everything ────────────────────────────────────────────────────

public class Main {
    public static void main(String[] args) {

        System.out.println("=== ArrayList ===");
        ListDemo.run();

        System.out.println("=== HashMap ===");
        MapDemo.run();

        System.out.println("=== HashSet ===");
        SetDemo.run();

        System.out.println("=== Generics ===");
        Box<String> strBox = new Box<>("hello");
        Box<Integer> intBox = new Box<>(42);
        strBox.describe();  // Box contains: hello (String)
        intBox.describe();  // Box contains: 42 (Integer)

        String[] words = {"one", "two", "three"};
        Integer[] nums = {1, 2, 3};
        Utils.printArray(words);  // one two three
        Utils.printArray(nums);   // 1 2 3
    }
}
