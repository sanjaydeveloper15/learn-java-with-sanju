// Lesson 3: Interfaces & Abstract Classes
// Coming from Node.js/PHP/Python: Java has two ways to define contracts/blueprints.
// Interface = pure contract (no implementation). Abstract class = partial blueprint.

// ─── 1. Interface ─────────────────────────────────────────────────────────────
// Like a TypeScript interface or PHP interface — defines what methods a class MUST have.
// A class can implement MULTIPLE interfaces (unlike single inheritance).

interface Drawable {
    void draw();  // no body — just the contract
}

interface Resizable {
    void resize(int factor);
}

// Implement multiple interfaces (not possible with extends)
class Circle implements Drawable, Resizable {
    int radius;

    Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing circle with radius " + radius);
    }

    @Override
    public void resize(int factor) {
        radius *= factor;
        System.out.println("Circle resized to radius " + radius);
    }
}

// ─── 2. Interface with default method ─────────────────────────────────────────
// Java 8+: interfaces can have default implementations (like a mixin in Python)

interface Logger {
    void log(String message);  // must implement

    default void logError(String message) {  // optional — has a default body
        System.out.println("[ERROR] " + message);
    }
}

class AppLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
    // logError() is inherited from the interface — no need to override
}

// ─── 3. Abstract Class ────────────────────────────────────────────────────────
// Like an abstract class in PHP/Python — can have both abstract methods (no body)
// and concrete methods (with body). Cannot be instantiated directly.

abstract class Shape {
    String color;

    Shape(String color) {
        this.color = color;
    }

    // Abstract method — subclasses MUST implement this
    abstract double area();

    // Concrete method — shared by all subclasses
    void describe() {
        System.out.println(color + " shape, area = " + area());
    }
}

class Rectangle extends Shape {
    double width, height;

    Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}

class Triangle extends Shape {
    double base, height;

    Triangle(String color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
    }

    @Override
    double area() {
        return 0.5 * base * height;
    }
}

// ─── 4. Interface vs Abstract Class — key difference ─────────────────────────
// Use interface when:  unrelated classes share behaviour (Drawable, Serializable)
// Use abstract class when: related classes share state/code (Shape hierarchy)

// ─── Main — run everything ────────────────────────────────────────────────────

public class Main {
    public static void main(String[] args) {

        // 1. Interface — multiple implementation
        Circle c = new Circle(5);
        c.draw();        // Drawing circle with radius 5
        c.resize(2);     // Circle resized to radius 10
        c.draw();        // Drawing circle with radius 10

        System.out.println("---");

        // 2. Interface default method
        AppLogger logger = new AppLogger();
        logger.log("Server started");          // [LOG] Server started
        logger.logError("Connection refused"); // [ERROR] Connection refused (from interface default)

        System.out.println("---");

        // 3. Abstract class — polymorphism
        Shape rect = new Rectangle("Blue", 4, 5);
        Shape tri  = new Triangle("Red", 6, 3);
        rect.describe();  // Blue shape, area = 20.0
        tri.describe();   // Red shape, area = 9.0
    }
}
