/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test.core;


import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author renat
 */
public final class Line {

    public final Point2D.Double p1;
    public final Point2D.Double p2;
    private double k;
    private double b;
    private final List<Line> intesections = new ArrayList();

    /**
     * 
     * @param p1
     * @param p2 
     */
    public Line(Point2D.Double p1, Point2D.Double p2) {
        this.p1 = p1;
        this.p2 = p2;

        if (!isVertical() && !isHorizontal()) {
            k = ( p2.y - p1.y) / (p2.x - p1.x);
            b = p1.y - k*p1.x;
        } else if (isHorizontal()) {
            b = p1.y;
        }
    }
    
    public double getIntercept() {
        return b;
    }
    
    public double getSlope() {
        return k;
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
    
    public boolean isMajor() {
        return p1.x < p2.x && p1.y < p2.y; 
    }
    
    public boolean isMinor() {
        return p1.x > p2.x && p1.y < p2.y;
    }
    
//    public boolean is45() {
//        return Math.abs(this.k) == 1.0;
//    }
    
    public List<Line> getIntersections() {
        return Collections.unmodifiableList(intesections);
    }

    public Point2D.Double getIntersection(Line line) {
        Point2D.Double result = null;
        
        if ((isVertical() && line.isVertical())
                || (isHorizontal() && line.isHorizontal())) {
            return null;
        } else if (isVertical() && line.isHorizontal()
                && (p1.x >= line.p1.x && p1.x <= line.p2.x)
                && (line.p1.y >= p1.y && line.p1.y <= p2.y)) {
            result = new Point2D.Double(p1.x, line.p1.y);
        } else if (isHorizontal() && line.isVertical()
                && (line.p1.x >= p1.x && line.p1.x <= p2.x)
                && (p1.y >= line.p1.y && p1.y <= line.p2.y)) {
            result = new Point2D.Double(line.p1.x, p1.y);
        } else if (isVertical() && line.isMajor()) {
            double y = line.getSlope() * p1.x + line.getIntercept();
            if (p1.y <= y && p2.y >= y 
                    && line.p1.x <= p1.x && line.p2.x >= p1.x
                    && line.p1.y <= y && line.p2.y >= y) {
                result = new Point2D.Double(p1.x, y);
            }
        } else if (isMajor() && line.isVertical()) {
            double y = k * line.p1.x + b;
            if (line.p1.y <= y && line.p2.y >= y 
                    && p1.x <= line.p1.x && p2.x >= line.p1.x
                    && p1.y <= y && p2.y >= y) {
                result = new Point2D.Double(line.p1.x, y);
            }
        } else if (isHorizontal() && line.isMajor()) {
            double x = (line.b - p1.y) / -line.k;
            if (p1.x <= x && p2.x >= x
                    && line.p1.x <= x && line.p2.x >= x
                    && line.p1.y <= p1.y && line.p2.y >= p1.y) {
                result = new Point2D.Double(x, p1.y);
            }
        } else if (isMajor() && line.isHorizontal()) {
            double x = (b - line.p1.y) / -k;
            if (line.p1.x <= x && line.p2.x >= x
                    && p1.x <= x && p2.x >= x
                    && p1.y <= line.p1.y && p2.y >= line.p1.y) {
                result = new Point2D.Double(x, line.p1.y);
            }
        } else if (isVertical() && line.isMinor()) {
            double y = line.getSlope() * p1.x + line.getIntercept();
            if (p1.y <= y && p2.y >= y 
                    && line.p1.x >= p1.x && line.p2.x <= p1.x
                    && line.p1.y <= y && line.p2.y >= y) {
                result = new Point2D.Double(p1.x, y);
            }
        } else if (isMinor() && line.isVertical()) {
            double y = k * line.p1.x + b;
            if (line.p1.y <= y && line.p2.y >= y 
                    && p1.x >= line.p1.x && p2.x <= line.p1.x
                    && p1.y <= y && p2.y >= y) {
                result = new Point2D.Double(line.p1.x, y);
            }
        } else if (isHorizontal() && line.isMinor()) {
            double x = (line.b - p1.y) / -line.k;
            if (p1.x <= x && p2.x >= x
                    && line.p1.x >= x && line.p2.x <= x
                    && line.p1.y <= p1.y && line.p2.y >= p1.y) {
                result = new Point2D.Double(x, p1.y);
            }
        } else if (isMinor() && line.isHorizontal()) {
            double x = (b - line.p1.y) / -k;
            if (line.p1.x <= x && line.p2.x >= x
                    && p1.x >= x && p2.x <= x
                    && p1.y <= line.p1.y && p2.y >= line.p1.y) {
                result = new Point2D.Double(x, line.p1.y);
            }
        } else if (k != line.k) {
            double x = (this.b - line.b) / (line.k - this.k);
            double y = this.k * x + this.b;
            if (
//                    isMajor() && line.isMajor()
                     p1.x <= x && p2.x >= x 
                    && p1.y <= y && p2.y >= y
                    && line.p1.x <= x && line.p2.x >= x
                    && line.p1.y <= y && line.p2.y >= y) {
                result = new Point2D.Double(x, y);
            } else if (
//                    isMinor() && isMinor()
                     p1.x >= x && p2.x <= x
                    && p1.y <= y && p2.y >= y
                    && line.p1.x >= x && line.p2.x <= x
                    && line.p1.y <= y && line.p2.y >= y) {
                result = new Point2D.Double(x, y);
            }
            else if (
//                    isMajor() && line.isMinor()
                    p1.x <= x && p2.x >= x
                    && p1.y <= y && p2.y >= y
                    && line.p1.x >= x && line.p2.x <= x
                    && line.p1.y <= y && line.p2.y >= y
                    ) {

                result = new Point2D.Double(x, y);
            } else if (
//                    isMinor() && line.isMajor()
                    p1.x >= x && p2.x <= x
                    && p1.y <= y && p2.y >= y
                    && line.p1.x <= x && line.p2.x >= x
                    && line.p1.y <= y && line.p2.y >= y
                    ) {
                result = new Point2D.Double(x, y);
            }
        }
        return result;
    }
    


    public boolean isIntersect(Line line) {
        return (null != getIntersection(line));
    }
    

}
