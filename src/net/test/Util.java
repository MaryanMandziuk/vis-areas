/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test;

import net.test.core.Line;
import java.awt.Point;
import java.awt.geom.Point2D;
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
                        Line l = new Line(new Point.Double(i, h1), new Point.Double(i, j));
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
                        Line l = new Line(new Point.Double(w1, j), new Point.Double(i, j));
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
    
    
  public static List<Line> scannMajor(int offset, int width, int height, int scanStep,
            Context context, double slope) {
        
        int pixels[][] = context.getPixels();
        int colorThreshold = context.getColorThreshold();
        int lineThreshold = context.getLineThreshold();
        
        List<Line> result = new ArrayList();
        
        
        
        for (int i = offset; i < width; i += scanStep) {
            double c = 0;
            double k = 0;
            int h1 = 0;
            double pi = 0, pj = 0;
            
            for ( int z = i; k < height && z < width; z ++) {
                
                k = (k + slope < height - 1) ? k += slope : height - 1;
                for (double j = c; j <= k; j ++) {

                    int value = 0xFF & pixels[z][(int)j];
                    if (value > colorThreshold && h1 > 0) {

                        if (h1 > lineThreshold) {
                            Line l = new Line(new Point2D.Double(pi, pj), new Point2D.Double( ((j-pj)/slope) + pi, j));
                            
                            result.add(l);
                        }

                        h1 = 0;
                    } else if (value < colorThreshold && h1 > 0 && z == 0) {
                        if (++h1 > lineThreshold) {
                            Line l = new Line(new Point2D.Double(pi, pj), new Point2D.Double(  ((j-pj)/slope) + pi, j));
                            result.add(l);
                        }
                    } else if (value < colorThreshold && h1 > 0) {
                        h1++;
                    }  else if (value < colorThreshold && h1 == 0 ) {
                        h1++;
                        
                        pi =j/slope + i;
                        
                        pj = j;
                    }
                }
                
                c += slope;
                if (c > k) {
                    break;
                }
            }
        }
        
        double c = 0;
        
        for (int i = scanStep -1; i < height; i += scanStep ) {
            
            c = i + 1;
            double k = c;
            int h1 = 0;
            double pi = 0, pj = 0;
            for (int z = 0; k < height && z < width; z ++) {
                
                k = (k + slope < height -1) ? k += slope : height - 1;
                for(double j = c; (int) j <= k && j < height; j ++) {
                    int value = 0xFF & pixels[z][(int)j];

                    if (value > colorThreshold && h1 > 0) {
                        if (h1 > lineThreshold) {
                            Line l = new Line(new Point2D.Double(pi, pj), new Point2D.Double(  ((j-pj)/slope) + pi, j));
                            result.add(l);
                        }

                        h1 = 0;
                    } else if (value < colorThreshold && h1 > 0 && j == height - 1) {
                        if (++h1 > lineThreshold) {
                            Line l = new Line(new Point2D.Double(pi, pj), new Point2D.Double( ((j-pj)/slope) + pi , j));
                            result.add(l);
                        }  
                    } else if (value < colorThreshold && h1 > 0) {
                        h1++;
                    } else if (value < colorThreshold && h1 == 0 ) {
                        h1++;
                        pi = ((j-i)/slope) -1;
                        pj = j;
                    } 
                }
                c += slope;
                if (c > k ) {
                    break;
                }
            }
        }
        return result;
        
    }
    
    
     public static List<Line> scannMinor(int offset, int width , int height, int scanStep,
            Context context, double slope) {
        
        int pixels[][] = context.getPixels();
        int colorThreshold = context.getColorThreshold();
        int lineThreshold = context.getLineThreshold();
        
        List<Line> result = new ArrayList();
        

        
        for (int i = width - 1; i >= offset; i -= scanStep) {
            
            int h1 = 0;
            double pi = 0, pj = 0;
            double c = 0;
            double k = 0;
            for (int z = i; k < height && z >= offset; z --) {
                k = (k + slope < height - 1) ? k += slope: height - 1;
                for (double j = c; j <= k; j ++) {
                    int value = 0xFF & pixels[z][(int)j];
                    if (value > colorThreshold && h1 > 0) { 

                        if (h1 > lineThreshold) {
                            Line l = new Line(new Point2D.Double(pi, pj), new Point2D.Double(pi - ((j-pj)/slope), j));
                            result.add(l);
                        }

                        h1 = 0;
                    } else if (value < colorThreshold && h1 > 0 && z == width - 1) {
                        if (++h1 > lineThreshold) {
                            Line l = new Line(new Point2D.Double(pi, pj), new Point2D.Double(  pi - ((j-pj)/slope) , j));
                            result.add(l);
                        }
                    } else if (value < colorThreshold && h1 > 0) {
                        h1++;
                    }  else if (value < colorThreshold && h1 == 0 ) {
                        h1++;
                        pi = (i -j/slope) +1;
                        pj = j;
                    }
                }
                c += slope;
                if ( c > k ) {
                    break;
                }
            }
        }
        
        double c = 0;
        for (int i = offset; i < height; i += scanStep) {
            int h1 = 0;
            double pi = 0, pj = 0;
            c = i +1;
            double k = c;
            for (int z = width - 1; k < height && z >= offset; z --) {
                k = (k + slope < height - 1) ? k += slope : height - 1;
                for(double j = c; j <= k && j < height; j ++) {
                    int value = 0xFF & pixels[z][(int)j];

                    if (value > colorThreshold && h1 > 0) { 
                        if (h1 > lineThreshold) {
                            Line l = new Line(new Point2D.Double(pi, pj), new Point2D.Double( pi- ((j - pj)/slope), j));
                            result.add(l);
                        }

                        h1 = 0;
                    } else if (value < colorThreshold && h1 > 0 && j == height - 1) {
                        if (++h1 > lineThreshold) {
                            Line l = new Line(new Point2D.Double(pi, pj), new Point2D.Double(pi- ((j - pj)/slope) , j));
                            result.add(l);
                        }  
                    } else if (value < colorThreshold && h1 > 0) {
                        h1++;
                    } else if (value < colorThreshold && h1 == 0 ) {
                        h1++;
                        pi = (width - (j-i)/slope) ;
                        pj = j;
                    } 
                }
                c += slope;
                if (c > k) {
                    break;
                }
            }
        }
        return result;
        
    }
    
    public static double slope(int width, int height, int degree) {
        int m = Math.min(width, height);
        double stepByPoint = 45.0 / m;
        double secondPoint = (degree / stepByPoint);
        double tmp = (m/secondPoint)* 100;
        double s = Math.round(tmp) ;
        return s/100;
}
}
