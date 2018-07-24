package com.github.billowen.gdsviewer;

import com.github.billowen.gds4java.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;


public class Viewer extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Structure structure = new Structure("Test");
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 200; j++) {
                Point p = new Point(j * 10, i * 10);
                Boundary b = Boundary.fromRect(p, new Size(5, 5));
                structure.addElement(b);
            }
        }

        System.out.println("Begin drawing...");

        long start = System.currentTimeMillis();
        StructureView root = new StructureView(structure);
        Scene scene = new Scene(root, 1000, 800);
        root.paint(new LayerPattern());


//        Boundary boundary = new Boundary();
//        List<Point> xy = Arrays.asList(new Point(10, 10), new Point(100, 10), new Point(100, 100), new Point(10, 100), new Point(10, 10));
//        boundary.setXy(xy);
//        BoundaryView b = new BoundaryView(boundary);
//        Rect bbox = boundary.getBBox();
//        b.setScaleX(4);
//        b.setScaleY(4);
//        System.out.println(b.getBoundsInLocal());
//        b.setFill(new LayerPattern().getFill(Color.RED));
//        b.setStroke(Color.RED);
//
//        root.getChildren().add(b);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
