package com.github.billowen.gds4java;

import com.github.billowen.gds4java.exception.UninitializedException;

import java.util.ArrayList;
import java.util.List;

public class Boundary extends Shape {
    @Override
    synchronized public void setXy(List<Point> xy) {
        if (xy.size() < 4) {
            throw new IllegalArgumentException("Boundary elements should have a minimum of 4 coordinates.");
        }
        if (xy.size() > 200) {
            throw new IllegalArgumentException("Boundary elements should have a maximum of 200 coordinates.");
        }
        if (!xy.get(0).equals(xy.get(xy.size() - 1))) {
            throw new IllegalArgumentException("The first and last coordinates of a boundary must coincide.");
        }
        this.xy = xy;
    }

    static public Boundary fromRect(Point p, Size size) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(p.getX(), p.getY()));
        points.add(new Point(p.getX() + size.getWidth(), p.getY()));
        points.add(new Point(p.getX() + size.getWidth(), p.getY() + size.getHeight()));
        points.add(new Point(p.getX(), p.getY() + size.getHeight()));
        points.add(new Point(p.getX(), p.getY()));
        Boundary b = new Boundary();
        b.setXy(points);

        return b;


    }

    @Override
    synchronized public Rect getBBox() throws UninitializedException {
        List<Point> XYs = getXy();
        if (XYs == null) {
            throw new UninitializedException("The xy of boundary element has not been initialized.");
        }

        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;
        for (Point p : XYs) {
            xMin = Integer.min(xMin, p.getX());
            xMax = Integer.max(xMax, p.getX());
            yMin = Integer.min(yMin, p.getY());
            yMax = Integer.max(yMax, p.getY());
        }

        return new Rect(new Point(xMin, yMin), new Size(xMax - xMin, yMax - yMin));
    }
}
