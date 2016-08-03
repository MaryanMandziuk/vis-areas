/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test.strategy;

import java.util.ArrayList;
import java.util.List;
import net.test.core.Area;
import net.test.Context;
import net.test.core.Line;
import net.test.ScanResult;
import net.test.ScanStrategy;
import net.test.Util;

/**
 *
 * @author renat
 */
public class DefaultScanStrategy implements ScanStrategy {

    protected final int[][] pixels;
    protected final int width;
    protected final int height;

    public DefaultScanStrategy(int[][] pixels, int width, int height) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;

    }

    public ScanResult scanImage(Context context) {
        List<Line> vertical = scanVertically(context);
        List<Line> horizontal = scanHorizontally(context);
        List<Line> intersections = scanIntersections(horizontal, vertical);
        List<Area> areas = restoreAreas(intersections);
        return new ScanResult(areas, vertical, horizontal);
    }

    /**
     *
     * @param intersectedLines
     */
    public final List<Area> restoreAreas(List<Line> intersectedLines) {
        List<Area> areas = new ArrayList();
        while (!intersectedLines.isEmpty()) {
            Line l = intersectedLines.get(0);
            Area a = restoreArea(l, intersectedLines);
            areas.add(a);
        }
        return areas;
    }

    /**
     *
     * @param line
     * @param lines
     * @return
     */
    public static Area restoreArea(Line line, List lines) {
        Area result = new Area();
        _restoreArea(line, result, lines);
        result.sort();
        return result;
    }

    /**
     *
     * @param line
     * @param area
     * @param lines
     */
    private static void _restoreArea(Line line, Area area, List<Line> lines) {
        if (line.isVertical()) {
            area.add(line);
        }
        lines.remove(line);

        for (Line l : line.getIntersections()) {
            if (!area.contains(l)) {
                _restoreArea(l, area, lines);
            }
        }
    }

    /**
     * can be parallel
     *
     * @param context
     * @return
     */
    public final List<Line> scanVertically(Context context) {
        return Util.scannVertically(0, width, height, context.getScanStep(), context);
    }

    /**
     * can be parallel
     *
     * @param context
     * @return
     */
    public final List<Line> scanHorizontally(Context context) {
        return Util.scannHorizontally(0, height, width, context.getScanStep(), context);
    }

    public final List<Line> scanIntersections(List<Line> horizontal, List<Line> vertical) {
        List<Line> result = new ArrayList();
        // search for intersections
        horizontal.stream().filter((l) -> (Util.findIntersections(l, vertical))).forEach((l) -> {
            result.add(l);
        });
        return result;
    }
}
