/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test;

import net.test.core.Area;
import net.test.core.Line;
import java.util.List;

/**
 *
 * @author renat
 */
public class ScanResult {

    private final List<Area> areas;
    private final List<Line> vertical;
    private final List<Line> horizontal;

    public ScanResult(List<Area> areas, List<Line> vertical, List<Line> horizontal) {
        this.areas = areas;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public List<Line> getVerticalLines() {
        return vertical;
    }

    public List<Line> getHorizontalLines() {
        return horizontal;
    }

}
