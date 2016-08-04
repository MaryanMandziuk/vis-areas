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
    
    
    public static List<Line> scannTopLeftToBottomRight(int offset, int width, int height, int scanStep,
            Context context) {
        
        int pixels[][] = context.getPixels();
        int colorThreshold = context.getColorThreshold();
        int lineThreshold = context.getLineThreshold();
        
        List<Line> result = new ArrayList();
        
        int c = 0;
        int k = 0;
        
        for (int i = offset; i < width; i += scanStep) {
            
            int h1 = 0;
            int pi = 0, pj = 0;
            c = i;
            k = (c < height - 1) ? c : height - 1;
            
            for (int j = 0; j <= k; j += scanStep, i -= scanStep) {

                int value = 0xFF & pixels[i][j];
                if (value > colorThreshold && h1 > 0) {
                    
                    if (h1 >= lineThreshold) {
                        Line l = new Line(new Point(pi, pj), new Point(i, j));
                        result.add(l);
                    }

                    h1 = 0;
                } else if (value < colorThreshold && h1 > 0 && i == 0) {
                    if (++h1 >= lineThreshold) {
                        Line l = new Line(new Point(pi, pj), new Point(i , j));
                        result.add(l);
                    }
                } else if (value < colorThreshold && h1 > 0) {
                    h1++;
                }  else if (value < colorThreshold && h1 == 0 ) {
                    h1++;
                    pi = i;
                    pj = j;
                }
            }
            i = c;
        }
        
        
        for (int j = 1; j < height; j += scanStep) {
            int h1 = 0;
            int pi = 0, pj = 0;
            c = j;
            k = width - height;
            if (k + c < 0) {
                k = -c;
            }
            
            for(int i = width - 1; i >= c + k; i -= scanStep, j += scanStep) {
                int value = 0xFF & pixels[i][j];

                if (value > colorThreshold && h1 > 0) {
                    if (h1 >= lineThreshold) {
                        Line l = new Line(new Point(pi, pj), new Point(i, j));
                        result.add(l);
                    }

                    h1 = 0;
                } else if (value < colorThreshold && h1 > 0 && j == height - 1) {
                    if (++h1 >= lineThreshold) {
                        Line l = new Line(new Point(pi, pj), new Point(i , j));
                        result.add(l);
                    }  
                } else if (value < colorThreshold && h1 > 0) {
                    h1++;
                } else if (value < colorThreshold && h1 == 0 ) {
                    h1++;
                    pi = i;
                    pj = j;
                } 
            }
            j = c;
        }
        return result;
        
    }
    
    
    public static List<Line> scannTopRightToLeftBottom(int offset, int width , int height, int scanStep,
            Context context) {
        
        int pixels[][] = context.getPixels();
        int colorThreshold = context.getColorThreshold();
        int lineThreshold = context.getLineThreshold();
        
        List<Line> result = new ArrayList();
        
        int c = 0;
        int k = 0;
        
        for (int i = width - 1; i >= offset; i -= scanStep) {
            
            int h1 = 0;
            int pi = 0, pj = 0;
            c = i;
            if (k > height - 1) {
                k = height - 1;
            }
            
            for (int j = 0; j <= k; j += scanStep, i += scanStep) {
                int value = 0xFF & pixels[i][j];
                if (value > colorThreshold && h1 > 0) { 
                    
                    if (h1 >= lineThreshold) {
                        Line l = new Line(new Point(pi, pj), new Point(i, j));
                        result.add(l);
                    }

                    h1 = 0;
                } else if (value < colorThreshold && h1 > 0 && i == width - 1) {
                    if (++h1 >= lineThreshold) {
                        Line l = new Line(new Point(pi, pj), new Point(i , j));
                        result.add(l);
                    }
                } else if (value < colorThreshold && h1 > 0) {
                    h1++;
                }  else if (value < colorThreshold && h1 == 0 ) {
                    h1++;
                    pi = i;
                    pj = j;
                }
            }
            k += scanStep;
            i = c;
        }
        
        k = width - height + 1;
        for (int j = 1; j < height; j += scanStep) {
            int h1 = 0;
            int pi = 0, pj = 0;
            c = j;
            if (k > 0 ) {
                k = 0;
            }
            
            for(int i = offset; i < height - c + k; i += scanStep, j += scanStep) {
                int value = 0xFF & pixels[i][j];

                if (value > colorThreshold && h1 > 0) { 
                    if (h1 >= lineThreshold) {
                        Line l = new Line(new Point(pi, pj), new Point(i, j ));
                        result.add(l);
                    }

                    h1 = 0;
                } else if (value < colorThreshold && h1 > 0 && j == height - 1) {
                    if (++h1 >= lineThreshold) {
                        Line l = new Line(new Point(pi, pj), new Point(i , j));
                        result.add(l);
                    }  
                } else if (value < colorThreshold && h1 > 0) {
                    h1++;
                } else if (value < colorThreshold && h1 == 0 ) {
                    h1++;
                    pi = i;
                    pj = j;
                } 
            }
            j = c;
            k+=scanStep;
        }
        return result;
        
    }
}
