package com.github.billowen.gds4java;

import java.util.List;

public class ARef extends Ref {
    private short row, col;
    private List<Point> xy;

    public short getRow() {
        return row;
    }

    public void setRow(short row) {
        this.row = row;
    }

    public short getCol() {
        return col;
    }

    public void setCol(short col) {
        this.col = col;
    }

    public List<Point> getXy() {
        return xy;
    }

    public void setXy(List<Point> xy) {
        this.xy = xy;
    }
}
