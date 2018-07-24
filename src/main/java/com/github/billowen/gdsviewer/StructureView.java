package com.github.billowen.gdsviewer;

import com.github.billowen.gds4java.Boundary;
import com.github.billowen.gds4java.Element;
import com.github.billowen.gds4java.Structure;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.List;

public class StructureView extends Group {
    private Structure structure;

    public StructureView(Structure structure) {
        this.structure = structure;
    }

    public void paint(LayerPattern pattern) {
        List<Element> elements = this.structure.getElements();
        for (Element e : elements) {
            if (e.getClass().equals(Boundary.class)) {
                BoundaryView node = new BoundaryView((Boundary)e);
                node.setFill(pattern.getFill(Color.RED));
                getChildren().add(node);
            }
        }
    }
}
