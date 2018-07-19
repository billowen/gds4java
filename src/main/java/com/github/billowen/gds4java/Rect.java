package com.github.billowen.gds4java;

import java.util.Objects;

public class Rect {
    private Point xy;
    private Size size;

    public Rect(Point xy, Size size) {
        this.xy = xy;
        this.size = size;
    }

    public Rect() {}

    public Point getXy() {
        return xy;
    }

    public void setXy(Point xy) {
        this.xy = xy;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rect rect = (Rect) o;
        return Objects.equals(xy, rect.xy) &&
                Objects.equals(size, rect.size);
    }

    @Override
    public int hashCode() {

        return Objects.hash(xy, size);
    }

    @Override
    public String toString() {
        return "Rect{" +
                "xy=" + xy +
                ", size=" + size +
                '}';
    }
}
