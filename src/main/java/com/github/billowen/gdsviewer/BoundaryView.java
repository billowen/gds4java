package com.github.billowen.gdsviewer;

import com.github.billowen.gds4java.Boundary;
import com.github.billowen.gds4java.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.StrokeType;

import java.util.List;


public class BoundaryView extends Polygon {
    private Boundary boundary;

    public BoundaryView(Boundary boundary) {
        List<Point> xy = boundary.getXy();
        Double[] points = new Double[xy.size() * 2];
        for (int i = 0; i < xy.size(); i++) {
            points[i * 2] = (double)xy.get(i).getX();
            points[i * 2 + 1] = (double)xy.get(i).getY();
        }
        getPoints().addAll(points);
        setStrokeType(StrokeType.INSIDE);
    }
}
