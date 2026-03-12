// ============================================================
// LESSON 1: Java Basics
// ============================================================
// HOW TO RUN:
//   javac test.java   (compiles to test.class)
//   java test         (runs it)
// ============================================================

public class test {

    public static void main(String[] args) {

        // ----------------------------------------
        // 1. PRINTING OUTPUT
        // ----------------------------------------
        System.out.println("Hello, Java!");       // prints + newline
        System.out.print("No newline here ");     // no newline
        System.out.print("same line\n");          // manual newline
        System.out.printf("Formatted: %s is %d years old%n", "Sanju", 30); // like printf in C/PHP


        // ----------------------------------------
        // 2. PRIMITIVE DATA TYPES
        // ----------------------------------------
        int age = 30;               // whole number         → PHP: $age = 30
        long bigNum = 9999999999L;  // large whole number   (note the L suffix)
        double price = 99.99;       // decimal              → JS: let price = 99.99
        float rate = 3.14f;         // less precise decimal (note the f suffix)
        boolean isActive = true;    // true/false
        char grade = 'A';           // single character (use single quotes!)
        byte small = 127;           // -128 to 127
        short medium = 32000;       // -32768 to 32767

        System.out.println("\n--- Primitives ---");
        System.out.println("age: " + age);
        System.out.println("bigNum: " + bigNum);
        System.out.println("price: " + price);
        System.out.println("isActive: " + isActive);
        System.out.println("grade: " + grade);


        // ----------------------------------------
        // 3. STRING (not a primitive — it's an Object)
        // ----------------------------------------
        String name = "Sanju";
        String lang = "Java";

        // Concatenation
        String msg = name + " is learning " + lang;
        System.out.println("\n--- Strings ---");
        System.out.println(msg);

        // Useful String methods
        System.out.println(name.length());          // 5       → PHP: strlen(), JS: .length
        System.out.println(name.toUpperCase());     // SANJU   → PHP: strtoupper()
        System.out.println(name.toLowerCase());     // sanju
        System.out.println(name.contains("an"));   // true
        System.out.println(name.replace("S", "B")); // Banju
        System.out.println(name.substring(1, 3));  // an  (from index 1, up to but not including 3)
        System.out.println(name.charAt(0));         // S   → JS: name[0]
        System.out.println("  trim me  ".trim());  // "trim me" → PHP: trim()

        // String comparison — NEVER use == for string content!
        String a = "hello";
        String b = "hello";
        System.out.println(a == b);         // true (but unreliable — checks reference!)
        System.out.println(a.equals(b));    // true (always use this for content comparison)
        System.out.println(a.equalsIgnoreCase("HELLO")); // true


        // ----------------------------------------
        // 4. TYPE CASTING
        // ----------------------------------------
        System.out.println("\n--- Type Casting ---");

        // Widening (automatic — safe, no data loss)
        int x = 100;
        double xAsDouble = x;       // int → double automatically
        System.out.println(xAsDouble); // 100.0

        // Narrowing (manual — you must cast explicitly)
        double pi = 3.99;
        int piAsInt = (int) pi;     // truncates decimal, does NOT round
        System.out.println(piAsInt);  // 3

        // String ↔ number conversion
        String numStr = "42";
        int parsed = Integer.parseInt(numStr);      // String → int   → PHP: intval(), JS: parseInt()
        double parsedD = Double.parseDouble("3.14");// String → double

        int num = 99;
        String numToStr = String.valueOf(num);       // int → String   → JS: String(num)
        // OR: String numToStr2 = Integer.toString(num);

        System.out.println(parsed + 1);   // 43
        System.out.println(numToStr);     // "99"


        // ----------------------------------------
        // 5. OPERATORS
        // ----------------------------------------
        System.out.println("\n--- Operators ---");

        // Arithmetic — same as every other language
        System.out.println(10 + 3);   // 13
        System.out.println(10 - 3);   // 7
        System.out.println(10 * 3);   // 30
        System.out.println(10 / 3);   // 3  ← INTEGER division! (both operands are int)
        System.out.println(10 / 3.0); // 3.3333 ← float division (one operand is double)
        System.out.println(10 % 3);   // 1  (modulo / remainder)

        // Increment / Decrement
        int counter = 5;
        counter++;   // counter = 6
        counter--;   // counter = 5
        System.out.println(counter);

        // Compound assignment
        int score = 10;
        score += 5;  // score = 15
        score *= 2;  // score = 30
        System.out.println(score);

        // Comparison operators (return boolean)
        System.out.println(5 > 3);    // true
        System.out.println(5 == 5);   // true
        System.out.println(5 != 3);   // true
        System.out.println(5 >= 5);   // true

        // Logical operators
        System.out.println(true && false);  // false  → PHP: &&, Python: and
        System.out.println(true || false);  // true   → PHP: ||, Python: or
        System.out.println(!true);          // false  → PHP: !, Python: not


        // ----------------------------------------
        // 6. CONTROL FLOW
        // ----------------------------------------
        System.out.println("\n--- Control Flow ---");

        // if / else if / else — identical to PHP/JS
        int userAge = 20;
        if (userAge >= 18) {
            System.out.println("Adult");
        } else if (userAge >= 13) {
            System.out.println("Teen");
        } else {
            System.out.println("Child");
        }

        // Ternary — same as JS/PHP
        String status = userAge >= 18 ? "adult" : "minor";
        System.out.println(status);

        // switch — similar to JS, but fall-through is default (need break)
        String day = "MON";
        switch (day) {
            case "MON":
                System.out.println("Monday");
                break;
            case "FRI":
                System.out.println("Friday");
                break;
            default:
                System.out.println("Other day");
        }


        // ----------------------------------------
        // 7. LOOPS
        // ----------------------------------------
        System.out.println("\n--- Loops ---");

        // for loop — identical to JS/PHP
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");  // 0 1 2 3 4
        }
        System.out.println();

        // while loop
        int w = 0;
        while (w < 3) {
            System.out.print(w + " ");  // 0 1 2
            w++;
        }
        System.out.println();

        // do-while (runs at least once)
        int d = 0;
        do {
            System.out.print(d + " ");
            d++;
        } while (d < 3);
        System.out.println();


        // ----------------------------------------
        // 8. ARRAYS (fixed size — unlike JS/PHP arrays)
        // ----------------------------------------
        System.out.println("\n--- Arrays ---");

        // Declare + initialize
        int[] numbers = {10, 20, 30, 40, 50};

        // Access by index
        System.out.println(numbers[0]);      // 10
        System.out.println(numbers.length);  // 5 (property, not method!)

        // Loop over array
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // Enhanced for-each loop (cleaner)
        for (int n : numbers) {
            System.out.print(n + " ");   // → Python: for n in numbers
        }
        System.out.println();

        // String array
        String[] fruits = {"apple", "banana", "cherry"};
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
