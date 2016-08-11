/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author renat
 */
public class Area {

    private Map<Line, Line> map = new HashMap();
    private List<Line> lines = new ArrayList();

    public Area() {
    }

    public int getSize() {
        return lines.size();
    }

    /**
     * 
     * @param l
     * @return 
     */
    public boolean contains(Line l) {
        return map.containsKey(l);
    }

    /**
     * 
     */
    public void sort() {
        if (lines.size() > 0) {
            if (lines.get(0).isVertical()) {
                verticalHorizontalSort();
            } else {
                angleSort();
            }
        }
    }
    
    private void angleSort() {
        lines.sort((Line o1, Line o2) -> (int)o1.getIntercept() - (int)o2.getIntercept());

        List<Line> filtered = new ArrayList();
        Line current = null;
        
        for (Line l : lines) {
            boolean skip = false;
            if (null != current && Math.abs(current.getIntercept() - l.getIntercept()) < 1) {

                if (current.p1.x > l.p2.x && current.p1.y > l.p2.y) {
                    current.p1.y = l.p1.y;
                    current.p1.x = l.p1.x;
                }
                if (current.p2.x < l.p2.x && current.p2.y < l.p2.y) {
                    current.p2.x = l.p2.x;
                    current.p2.y = l.p2.y;
                }

                skip = true; 
            }
            if (!skip) {
                current = l;
                filtered.add(current);
            }
        }

        lines = filtered;
    }
    
    
    private void verticalHorizontalSort() {
        lines.sort((Line o1, Line o2) -> (int)o1.p1.x - (int)o2.p1.x);

        List<Line> filtered = new ArrayList();
        Line current = null;
        
        for (Line l : lines) {
            boolean skip = false;
            if (null != current && current.p1.x == l.p1.x) {

                if (current.p2.y < l.p2.y) {
                    current.p2.y = l.p2.y;
                }
                if (current.p1.y > l.p1.y) {
                    current.p1.y = l.p1.y;
                }

                skip = true;
            }
            if (!skip) {
                current = l;
                filtered.add(current);
            }
        }

        lines = filtered;
    }
    /**
     * 
     * @param line 
     */
    public void add(Line line) {
        lines.add(line);
        map.put(line, line);
    }

    /**
     * 
     * @return 
     */
    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
