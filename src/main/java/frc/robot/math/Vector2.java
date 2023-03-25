package frc.robot.math;

public class Vector2 {
    public double x, y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Static mathematics
    public static Vector2 add(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x + vec2.x, vec1.y + vec2.y);
    }
    public static Vector2 sub(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x - vec2.x, vec1.y - vec2.y);
    }
    public static Vector2 mul(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x * vec2.x, vec1.y * vec2.y);
    }
    public static Vector2 div(Vector2 vec1, Vector2 vec2) {
        return new Vector2(vec1.x / vec2.x, vec1.y / vec2.y);
    }

    // Instancs maths
    public Vector2 plus(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }
    public Vector2 minus(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }
    public Vector2 times(Vector2 other) {
        return new Vector2(this.x * other.x, this.y * other.y);
    }
    public Vector2 divided(Vector2 other) {
        return new Vector2(this.x / other.x, this.y / other.y);
    }
    public Vector2 plus(double other) {
        return new Vector2(this.x + other, this.y + other);
    }
    public Vector2 minus(double other) {
        return new Vector2(this.x - other, this.y - other);
    }
    public Vector2 times(double other) {
        return new Vector2(this.x * other, this.y * other);
    }
    public Vector2 divided(double other) {
        return new Vector2(this.x / other, this.y / other);
    }

    public double length() {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }

    public Vector2 norm() {
        double len = this.length();
        if(len == 0)
            len = 1;
        return this.divided(new Vector2(len, len));
    }

}
