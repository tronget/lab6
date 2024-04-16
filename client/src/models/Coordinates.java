package models;

import utility.Validatable;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Validatable, Serializable {
    private int x; //Значение поля должно быть больше -768
    private double y;

    public int getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Coordinates(int x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean validate() {
        return x > -768;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && Double.compare(y, that.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
