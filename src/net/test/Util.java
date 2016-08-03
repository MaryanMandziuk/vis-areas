/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test;

import net.test.core.Line;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author renat
 */
public final class Util {

    /**
     * 
     * @param line
     * @param verticalLines
     * @return 
     */
    public static boolean findIntersections(Line line, List<Line> verticalLines) {
        boolean result = false;

        for (Line l : verticalLines) {
            if (line.isIntersect(l)) {
                line.addIntersection(l);
                l.addIntersection(line);
                result = true;
            }
        }
        return result;
    }

    /**
     * 
     * @param offset
     * @param widthLimit
     * @param height
     * @param scanStep
     * @param context
     * @return 
     */
    public static List<Line> scannVertically(int offset, int widthLimit, int height, int scanStep,
            Context context) {

        int pixels[][] = context.getPixels();
        int colorThreshold = context.getColorThreshold();
        int lineThreshold = context.getLineThreshold();

        List<Line> result = new ArrayList();

        for (int i = offset; i < widthLimit; i += scanStep) {
            int h1 = -1;
            for (int j = 0; j < height; j += scanStep) {
                int value = 0xFF & pixels[i][j];

                if (value > colorThreshold && h1 > 0) { // we had a segment legnth
                    if (j - h1 > lineThreshold) {
                        Line l = new Line(new Point(i, h1), new Point(i, j));
                        result.add(l);
                    }

                    h1 = -1;
                } else if (value < colorThreshold && h1 < 0) {
                    h1 = j;
                }
            }
        }
        return result;
    }

    /**
     * 
     * @param offset
     * @param heightLimit
     * @param width
     * @param scanStep
     * @param context
     * @return 
     */
    public static List<Line> scannHorizontally(int offset, int heightLimit, int width, int scanStep,
            Context context) {

        final int pixels[][] = context.getPixels();
        final int colorThreshold = context.getColorThreshold();
        final int lineThreshold = context.getLineThreshold();

        final List<Line> result = new ArrayList();

        for (int j = 0; j < heightLimit; j += scanStep) {
            int w1 = -1;
            for (int i = 0; i < width; i += scanStep) {
                int value = 0xFF & pixels[i][j];

                if (value > colorThreshold && w1 > 0) { // we had segment legnth
                    if (i - w1 > lineThreshold) {
                        Line l = new Line(new Point(w1, j), new Point(i, j));
                        result.add(l);
                    }
                    w1 = -1;
                } else if (value < colorThreshold && w1 < 0) {
                    w1 = i;
                }
            }
        }

        return result;
    }
}
