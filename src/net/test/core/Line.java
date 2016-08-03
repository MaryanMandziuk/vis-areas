/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test.core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author renat
 */
public final class Line {

    public final Point p1;
    public final Point p2;
    private double k;
    private double b;
    private final List<Line> intesections = new ArrayList();

    /**
     * 
     * @param p1
     * @param p2 
     */
    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;

        if (!isVertical() && !isHorizontal()) {
            k = ((double) p2.y - p1.y) / (p2.x - p1.x);
            b = p1.y;
        }
    }

    public void addIntersection(Line l) {
        intesections.add(l);
    }

    public boolean isVertical() {
        return p1.x == p2.x;
    }

    public boolean isHorizontal() {
        return p1.y == p2.y;
    }

    public List<Line> getIntersections() {
        return Collections.unmodifiableList(intesections);
    }

    public Point getIntersection(Line line) {
        Point result = null;

        if ((isVertical() && line.isVertical())
                || (isHorizontal() && line.isHorizontal())) {
            return null;
        } else if (isVertical() && line.isHorizontal()
                && (p1.x >= line.p1.x && p1.x <= line.p2.x)
                && (line.p1.y >= p1.y && line.p1.y <= p2.y)) {
            result = new Point(p1.x, line.p1.y);
        } else if (isHorizontal() && line.isVertical()
                && (line.p1.x >= p1.x && line.p1.x <= p2.x)
                && (p1.y >= line.p1.y && p1.y <= line.p2.y)) {
            result = new Point(line.p1.x, p1.y);
        }

        return result;
    }

    public boolean isIntersect(Line line) {
        return (null != getIntersection(line));
    }
}
