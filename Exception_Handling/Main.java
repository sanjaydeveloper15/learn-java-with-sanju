// Lesson 5: Exception Handling
// Coming from Node.js/PHP/Python: try/catch works the same way.
// Java adds: checked exceptions (compiler forces you to handle them).

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

// ─── 1. Basic try / catch / finally ──────────────────────────────────────────
// Same as JS/PHP — try the risky code, catch the error, finally always runs.

class BasicDemo {
    static void run() {
        try {
            int result = 10 / 0;  // throws ArithmeticException
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());  // / by zero
        } finally {
            System.out.println("Finally always runs");  // like JS finally
        }
    }
}

// ─── 2. Multiple catch blocks ─────────────────────────────────────────────────
// Catch different exception types separately — most specific first.

class MultiCatchDemo {
    static void run(String[] args, int index) {
        try {
            int num = Integer.parseInt(args[index]);  // could throw two different exceptions
            System.out.println("Parsed: " + num);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No argument at index " + index);
        } catch (NumberFormatException e) {
            System.out.println("Not a number: " + e.getMessage());
        }
    }
}

// ─── 3. Checked vs Unchecked exceptions ──────────────────────────────────────
// Unchecked (RuntimeException) — compiler doesn't force you to handle (ArithmeticException, NullPointerException)
// Checked — compiler FORCES you to handle or declare with 'throws' (IOException, SQLException)
// No equivalent in JS/Python — unique to Java.

class CheckedDemo {
    // Must declare 'throws IOException' OR wrap in try/catch
    static void readFile(String path) throws IOException {
        FileReader reader = new FileReader(new File(path));
        reader.close();
    }

    static void run() {
        try {
            readFile("nonexistent.txt");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}

// ─── 4. Custom exceptions ─────────────────────────────────────────────────────
// Like extending Error in JS or Exception in Python.

class InsufficientFundsException extends RuntimeException {
    double amount;

    InsufficientFundsException(double amount) {
        super("Insufficient funds. Short by: " + amount);
        this.amount = amount;
    }
}

class Wallet {
    private double balance;

    Wallet(double balance) {
        this.balance = balance;
    }

    void withdraw(double amount) {
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);  // like JS: throw new Error()
        }
        balance -= amount;
        System.out.println("Withdrew " + amount + ", balance: " + balance);
    }
}

// ─── 5. try-with-resources ────────────────────────────────────────────────────
// Auto-closes resources (files, DB connections) — like Python's 'with' statement.

class TryWithResourcesDemo {
    static void run() {
        // StringReader auto-closed after the block — no need for finally { reader.close() }
        try (StringReader reader = new StringReader("hello")) {
            System.out.println("Reader ready: " + reader.ready());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

// ─── Main — run everything ────────────────────────────────────────────────────

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Basic try/catch/finally ===");
        BasicDemo.run();

        System.out.println("\n=== Multiple catch ===");
        MultiCatchDemo.run(new String[]{}, 0);           // ArrayIndexOutOfBoundsException
        MultiCatchDemo.run(new String[]{"abc"}, 0);      // NumberFormatException
        MultiCatchDemo.run(new String[]{"42"}, 0);       // success

        System.out.println("\n=== Checked Exception ===");
        CheckedDemo.run();

        System.out.println("\n=== Custom Exception ===");
        Wallet wallet = new Wallet(100);
        wallet.withdraw(40);
        try {
            wallet.withdraw(200);  // throws InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println("\n=== try-with-resources ===");
        TryWithResourcesDemo.run();
    }
}
