package com.github.billowen.gds4java;

import com.github.billowen.gds4java.exception.UninitializedException;

import java.util.ArrayList;
import java.util.List;

public class Structure {
    private List<Element> elements = new ArrayList<>();
    private String name;

    public Structure(String name) {
        this.name = name;
    }

    synchronized public  List<Element> getElements() {
        return elements;
    }

    synchronized public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    synchronized public void addElement(Element element) {
        this.elements.add(element);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    synchronized public Rect getBBox() {
        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        for (Element e : elements) {
            if (e instanceof Shape) {
                try {
                    Rect bbox = ((Shape) e).getBBox();
                    Point p = bbox.getXy();
                    Size sz = bbox.getSize();
                    x1 = Integer.min(x1, p.getX());
                    y1 = Integer.min(y1, p.getY());
                    x2 = Integer.max(x2, p.getX() + sz.getWidth());
                    y2 = Integer.max(y2, p.getY() + sz.getHeight());
                } catch (UninitializedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (x1 > x2 || y1 > y2) {
            return null;
        }
        else {
            return new Rect(new Point(x1, y1), new Size(x2 - x1, y2 - y1));
        }
    }
}
