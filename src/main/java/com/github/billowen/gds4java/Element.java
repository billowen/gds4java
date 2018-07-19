package com.github.billowen.gds4java;

public abstract class Element {
    private short elflags = 0;
    private short plex = 0;

    public short getElflags() {
        return elflags;
    }

    public void setElflags(short elflags) {
        this.elflags = elflags;
    }

    public short getPlex() {
        return plex;
    }

    public void setPlex(short plex) {
        this.plex = plex;
    }
}
