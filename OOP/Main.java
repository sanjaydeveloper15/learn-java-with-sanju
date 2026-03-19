// Lesson 2: OOP — Classes, Objects, Constructors
// Coming from Node.js/PHP/Python: Java classes are strict — no dynamic properties,
// everything must be declared with a type upfront.

// ─── 1. Basic Class + Constructor ────────────────────────────────────────────

class Car {
    // Fields (like object properties, but typed and declared upfront)
    String brand;
    int year;

    // Constructor (like __init__ in Python or constructor() in JS)
    Car(String brand, int year) {
        this.brand = brand;  // 'this' = the current instance (same as JS/Python)
        this.year = year;
    }

    // Method
    void describe() {
        System.out.println(brand + " (" + year + ")");
    }
}

// ─── 2. Encapsulation — private fields + getters/setters ─────────────────────
// Best practice: keep fields private, expose via methods

class BankAccount {
    private double balance;  // private = only accessible inside this class

    BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Getter
    double getBalance() {
        return balance;
    }

    // Method with validation
    void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }
}

// ─── 3. Inheritance ───────────────────────────────────────────────────────────
// Like 'extends' in JS or class inheritance in Python

class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    void speak() {
        System.out.println(name + " makes a sound");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);  // call parent constructor (like super().__init__() in Python)
    }

    @Override  // annotation — tells compiler this method overrides parent's
    void speak() {
        System.out.println(name + " barks");
    }
}

// ─── 4. Static vs Instance ────────────────────────────────────────────────────
// static = belongs to the class, not instances (like class methods in Python)

class MathUtils {
    static int square(int n) {
        return n * n;
    }
}

// ─── Main — run everything ────────────────────────────────────────────────────

public class Main {
    public static void main(String[] args) {

        // 1. Basic class usage
        Car car1 = new Car("Toyota", 2020);
        Car car2 = new Car("Honda", 2022);
        car1.describe();  // Toyota (2020)
        car2.describe();  // Honda (2022)

        System.out.println("---");

        // 2. Encapsulation
        BankAccount account = new BankAccount(1000);
        account.deposit(500);
        account.withdraw(200);
        account.deposit(500);
        System.out.println("Balance: " + account.getBalance());  // 1300.0

        System.out.println("---");

        // 3. Inheritance + override
        Animal cat = new Animal("Cat");
        Dog dog = new Dog("Rex");
        cat.speak();  // Cat makes a sound
        dog.speak();  // Rex barks

        System.out.println("---");

        // 4. Static method — call on class, not instance
        System.out.println("5 squared = " + MathUtils.square(5));  // 25
    }
}
