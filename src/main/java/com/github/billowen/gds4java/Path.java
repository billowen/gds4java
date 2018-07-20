package com.github.billowen.gds4java;

import com.github.billowen.gds4java.exception.UninitializedException;

import java.util.List;

public class Path extends Shape {
    private short pathtype = 0;
    private int width = 0;

    public short getPathtype() {
        return pathtype;
    }

    public void setPathtype(short pathtype) {
        this.pathtype = pathtype;
    }

    public int getWidth() {
        return width;
    }

    synchronized public void setWidth(int width) {
        if (width % 2 != 0) {
            throw new IllegalArgumentException("The width of path elements should be even number.");
        }
        this.width = width;
    }

    /**
     * Set the coordinates of path element.
     * @param xy The coordinate of path element. And the number of coordinates should between 2 and 200. And
     *           currently, only orthogonal path is supported.
     * @throws IllegalArgumentException The number of coordinates should between 2 and 200.
     * And currently, only orthogonal path is supported.
     */
    @Override
    synchronized public void setXy(List<Point> xy) {
        if (xy.size() < 2) {
            throw new IllegalArgumentException("Path elements should have a minimum of 2 coordinates.");
        }
        if (xy.size() > 200) {
            throw new IllegalArgumentException("Path elements should have a maximum of 200 coordinates.");
        }
        for (int i = 0; i < xy.size() - 1; i++) {
            Point p1 = xy.get(i);
            Point p2 = xy.get(i + 1);
            if (p1.getX() != p2.getX() && p1.getY() != p2.getY()) {
                throw new IllegalArgumentException("Path elements only support orthogonal turn.");
            }
        }
        this.xy = xy;
    }

    @Override
    public Rect getBBox() throws UninitializedException {
        List<Point> XYs = getXy();
        if (XYs == null)
            throw new UninitializedException("The xy of path element has not been initialized.");
        if (width <= 0)
            throw new UninitializedException("The width of path element has not be initialized.");

        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

        for (int i = 0; i < XYs.size() - 1; i++) {
            Point p1 = XYs.get(i);
            Point p2 = XYs.get(i + 1);

            int ext = width / 2;
            if (i == 0|| i == XYs.size() - 2) {
                if (pathtype == 0) {
                    ext = 0;
                }
            }

            if (p1.getX() == p2.getX()) {
                xMin = Integer.min(xMin, p1.getX() - width / 2);
                xMax = Integer.max(xMax, p1.getX() + width / 2);
                yMin = Integer.min(yMin, Integer.min(p1.getY(), p2.getY()) - ext);
                yMax = Integer.max(yMax, Integer.max(p1.getY(), p2.getY()) + ext);
            }
            else {
                xMin = Integer.min(xMin, Integer.min(p1.getX(), p2.getX()) - ext);
                xMax = Integer.max(xMax, Integer.max(p1.getX(), p2.getX()) + ext);
                yMin = Integer.min(yMin, p1.getY() - width / 2);
                yMax = Integer.max(yMax, p1.getY() + width / 2);
            }
        }

        return new Rect(new Point(xMin, yMin), new Size(xMax - xMin, yMax - yMin));
    }
}
