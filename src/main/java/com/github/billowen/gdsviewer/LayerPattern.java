package com.github.billowen.gdsviewer;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class LayerPattern {
    private short[] data = {1, 1, 0, 1, 1, 0};
    private int width = 3, height = 2;

    public ImagePattern getFill(Color color) {
        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();
        for (int i = 0; i < data.length; i++) {
            int row = i / width;
            int col = i % width;
            if (data[i] != 0) {
                pixelWriter.setColor(col, row, color);
            }
        }
        return new ImagePattern(image, 0, 0, 1, 1, false);


    }

}
