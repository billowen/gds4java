package com.github.billowen.gds4java;

import com.github.billowen.gds4java.exception.UninitializedException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PathTest {

    @Test(expected = IllegalArgumentException.class)
    public void setXy_shouldThrowExceptionWhenArgumentListIsSmallerThan2() {
        Path b = new Path();
        List<Point> xy = Collections.singletonList(new Point(1, 1));
        b.setXy(xy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setXy_shouldThrowExceptionWhenArgumentListIsBiggerThan200() {
        List<Point> xy = new ArrayList<>();
        for (int i = 0; i < 202; i++) {
            xy.add(new Point(1, 1));
        }
        Path b = new Path();
        b.setXy(xy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setXy_shouldThrowExceptionWhenPathIsNotOrthogonal() {
        List<Point> xy = Arrays.asList(new Point(1, 1), new Point(2, 2));
        Path b = new Path();
        b.setXy(xy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWidth_shouldThrowExceptionWhenWidthIsNotEvenNumber() {
        Path p = new Path();
        p.setWidth(5);
    }

    @Test(expected = UninitializedException.class)
    public void getBBox_shouldThrowExceptionWhenXyIsNotInitialized() throws UninitializedException {
        Path p = new Path();
        p.getBBox();
    }

    @Test(expected = UninitializedException.class)
    public void getBBox_shouldThrowExceptionWhenWidthIsSmallerThan0() throws UninitializedException {
        Path p = new Path();
        p.setWidth(0);
        p.getBBox();
    }

    @Test
    public void test_getBBoxForPathtypeIsZero() throws UninitializedException {
        Path p = new Path();
        p.setXy(Arrays.asList(new Point(1, 1), new Point(1, 5), new Point(5, 5)));
        p.setWidth(2);

        Rect actual = p.getBBox();
        Rect expect = new Rect(new Point(0, 1), new Size(5, 5));

        assertThat(actual, is(expect));
    }

    @Test
    public void test_getBBoxForPathtypeIsNotZero() throws UninitializedException {
        Path p = new Path();
        p.setXy(Arrays.asList(new Point(1, 1), new Point(1, 5), new Point(5, 5)));
        p.setWidth(2);
        p.setPathtype((short) 1);

        Rect actual = p.getBBox();
        Rect expect = new Rect(new Point(0, 0), new Size(6, 6));

        assertThat(actual, is(expect));
    }

}