package com.github.billowen.gds4java;

import com.github.billowen.gds4java.exception.UninitializedException;

import java.util.List;

public abstract class Shape extends Element {
    protected short layer = -1, datatype = -1;
    protected List<Point> xy;

    synchronized public short getLayer() {
        return layer;
    }

    synchronized public void setLayer(short layer) {
        if (layer < 0 || layer > 255)
            throw new IllegalArgumentException("The value of the layer must be in the range of 0 t0 255.");
        this.layer = layer;
    }

    synchronized public short getDatatype() {
        return datatype;
    }

    synchronized public void setDatatype(short datatype) {
        if (datatype < 0 || datatype > 255)
            throw new IllegalArgumentException("The value of the datatype must be in the range of 0 to 255.");
        this.datatype = datatype;
    }

    synchronized public List<Point> getXy() {
        return xy;
    }

    abstract public void setXy(List<Point> xy);

    abstract public Rect getBBox() throws UninitializedException;
}
