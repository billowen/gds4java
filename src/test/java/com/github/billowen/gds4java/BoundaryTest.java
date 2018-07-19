package com.github.billowen.gds4java;

import com.github.billowen.gds4java.exception.UninitializedException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BoundaryTest {

    @Test(expected = IllegalArgumentException.class)
    public void getLayer_shouldThrowExceptionWhenArgumentSmallerThanZero() {
        Boundary b = new Boundary();
        b.setLayer((short)-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDataType_shouldThrowExceptionWhenArgumentSmallerThanZero() {
        Boundary b = new Boundary();
        b.setDatatype((short)-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLayer_shouldThrowExceptionWhenArgumentLargerThan255() {
        Boundary b = new Boundary();
        b.setLayer((short) 256);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDatatype_shouldThrowExceptionWhenArgumentLargerThan255() {
        Boundary b = new Boundary();
        b.setLayer((short) 256);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setXy_shouldThrowExceptionWhenArgumentListIsSmallerThan4() {
        Boundary b = new Boundary();
        List<Point> xy = Arrays.asList(new Point(1, 1), new Point(2, 2));
        b.setXy(xy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setXy_shouldThrowExceptionWhenArgumentListIsBiggerThan200() {
        List<Point> xy = new ArrayList<>();
        for (int i = 0; i < 202; i++) {
            xy.add(new Point(1, 1));
        }
        Boundary b = new Boundary();
        b.setXy(xy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setXy_shouldThrowExceptionWhenFirstAndLastCoordinatesIsNotCoincide() {
        List<Point> xy = Arrays.asList(new Point(1, 1), new Point(1, 1), new Point(1, 1), new Point(2, 2));
        Boundary b = new Boundary();
        b.setXy(xy);
    }

    @Test(expected = UninitializedException.class)
    public void getBBox_shouldThrowExceptionWhenXyIsNotInitialized() throws UninitializedException {
        Boundary b = new Boundary();
        b.getBBox();
    }

    @Test
    public void test_getBBox() throws UninitializedException {
        Boundary b = new Boundary();
        b.setXy(Arrays.asList(new Point(1, 1), new Point(4, 5), new Point(10, -2), new Point(1, 1)));
        Rect actual = b.getBBox();
        Rect expect = new Rect(new Point(1, -2), new Size(9, 7));
        assertThat(actual, is(expect));
    }
}