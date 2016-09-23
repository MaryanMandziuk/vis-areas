/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.test;

import java.awt.geom.Point2D;
import java.util.List;
import net.test.core.Line;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author maryan
 */
public class UtilNGTest {

    public UtilNGTest() {
    }


    /**
     * Test of scannTopLeftToBottomRight method, of class Util.
     */
    @Test
    public void testScannMajor() {
        System.out.println("Test: scannMajor()");

        int[][] pixels = {
            {0,2,2,2,2,2,2,2,2},
            {2,0,2,2,2,2,2,2,2},
            {2,2,0,2,2,2,2,2,2},
            {2,2,2,0,2,2,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,2,0,2,2,2},
            {2,2,2,2,2,2,0,2,2},
            {2,2,2,2,2,2,2,0,2},
            {2,2,2,2,2,2,2,2,0},
        };
 
        int offset = 0;
        int width = pixels.length;
        int height = pixels[0].length;
        int scanStep = 1;
        double slope = 1.0;
        Context context = new Context(pixels, 1, 2);
        List<Line> result = Util.scannMajor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(0, 0), "w=h 45 degree diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(7, 7), "w=h 45 degree diagonal faild second point");
        
        
        pixels = new int[][]{
            {2,2,2,0,2,2,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,2,0,2,2,2},
            {2,2,2,2,2,2,0,2,2},
            {2,2,2,2,2,2,2,0,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 1.0;
        context = new Context(pixels, 1, 2);
        result = Util.scannMajor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(0, 3), "w=h 45 degree line over diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(5, 8), "w=h 45 degree line over diagonal faild second point");
        
        
        
        pixels = new int[][]{
            {2,2,2,2,2,0,2,2,2},
            {2,2,2,2,2,2,0,2,2},
            {2,2,2,2,2,2,2,0,2},
            {2,2,2,2,2,2,2,2,0},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 1.0;
        context = new Context(pixels, 1, 2);
        result = Util.scannMajor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(0, 5), "w=h 45 degree line over diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(3, 8), "w=h 45 degree line over diagonal faild second point"); 
        
        
        pixels = new int[][]{
            {2,2,2,2,2,2,0,2,2},
            {2,2,2,2,2,2,2,0,2},
            {2,2,2,2,2,2,2,2,0},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 1.0;
        context = new Context(pixels, 1, 2);
        result = Util.scannMajor(offset, width, height, scanStep, context, slope);
        
        assertEquals(result.size(), 0);
        
        
        pixels = new int[][]{
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,0,2,2,2,2,2,2},
            {2,2,2,0,2,2,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,2,0,2,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 1.0;
        context = new Context(pixels, 1, 2);
        result = Util.scannMajor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(4, 2), "w=h 45 degree line under diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(8, 6), "w=h 45 degree line under diagonal faild second point"); 
        
        
        pixels = new int[][]{
            {2,2,2,0,0,0,2,2,2},
            {2,2,2,0,0,0,2,2,2},
            {2,2,2,0,0,0,0,2,2},
            {2,2,2,0,0,0,0,2,2},
            {2,2,2,2,0,0,0,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 25.0;
        context = new Context(pixels, 1, 2);
        result = Util.scannMajor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(0.12, 3), "w=h 25 degree line  faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(0.24, 6), "w=h 25 degree line  faild second point"); 
        
        
        pixels = new int[][]{
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,0,0,2,2,2},
            {2,2,2,0,0,0,0,2,2},
            {2,2,2,2,0,0,0,2,2},
            {2,2,2,2,0,0,0,2,2},
            {2,2,2,2,0,0,0,0,2},
            {2,2,2,2,2,0,0,0,2},
            {2,2,2,2,2,0,0,0,0},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 25.0;
        context = new Context(pixels, 1, 2);
        result = Util.scannMajor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(3.12, 3), "w=h 25 degree line  faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(3.2800000000000002, 7), "w=h 25 degree line  faild second point"); 
        
        
        pixels = new int[][]{
            {2,2,2,2,2,2,2,2,2,2,2,2},
            {2,2,0,2,2,2,2,2,2,2,2,2},
            {2,2,2,0,0,2,2,2,2,2,2,2},
            {2,2,2,0,0,0,2,2,2,2,2,2},
            {2,2,2,2,0,2,2,2,2,2,2,2},
            {2,2,2,2,0,0,0,0,2,2,2,2},
            {2,2,2,2,2,0,0,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 1.0;
        context = new Context(pixels, 1, 2);
        result = Util.scannMajor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(3, 3), "w<h 45 degree  diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(6, 6), "w<h 45 degree  diagonal faild second point"); 
        
        
        pixels = new int[][]{
            {2,2,2,2,2,2,2,2,2,2,2,2},
            {2,2,0,0,2,2,2,2,2,2,2,2},
            {2,2,2,0,0,2,2,2,2,2,2,2},
            {2,2,2,0,0,0,2,2,2,2,2,2},
            {2,2,2,0,0,2,2,2,2,2,2,2},
            {2,2,2,0,0,0,0,0,2,2,2,2},
            {2,2,2,0,0,0,0,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 22.5;
        context = new Context(pixels, 1, 2);

        result = Util.scannMajor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(3.1333333333333333, 3.0), "w<h 2 degree  diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(3.2666666666666666, 6.0), "w<h 2 degree  diagonal faild second point");
        
        
        pixels = new int[][]{
            {2,2,0,2,2,2},
            {2,0,2,0,2,2},
            {2,0,0,2,0,2},
            {2,2,0,0,2,2},
            {2,2,2,0,0,2},
            {2,2,2,2,0,0},
            {2,2,2,2,2,2},
            {2,2,2,2,2,2},

        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 1.0;
        context = new Context(pixels, 1, 2);

        result = Util.scannMajor(offset, width, height, scanStep, context, slope);
        
        assertEquals(result.size(),3);
        assertEquals(result.get(0).p1, new Point2D.Double(1.0, 1.0), "w>h 45 degree  diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(4.0, 4.0), "w>h 45 degree  diagonal faild second point");
        
        
    }
    
    @Test
    public void testMinor() {
        System.out.println("Test: scannMinor()");

        int[][] pixels = {
            {2,2,2,2,2,2,2,2,0},
            {2,2,2,2,2,2,2,0,2},
            {2,2,2,2,2,2,0,2,2},
            {2,2,2,2,2,0,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,0,2,2,2,2,2},
            {2,2,0,2,2,2,2,2,2},
            {2,0,2,2,2,2,2,2,2},
            {0,2,2,2,2,2,2,2,2},
        };
 
        int offset = 0;
        int width = pixels.length;
        int height = pixels[0].length;
        int scanStep = 1;
        double slope = 1.0;
        Context context = new Context(pixels, 1, 2);
        List<Line> result = Util.scannMinor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(8, 0), "w=h 45 degree diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(1, 7), "w=h 45 degree diagonal faild second point");
        
        
        pixels = new int[][]{
            {2,2,2,0,2,2,2,2,2},
            {2,2,0,2,2,2,2,2,2},
            {2,0,2,2,2,2,2,2,2},
            {0,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 1.0;
        context = new Context(pixels, 1, 2);
        result = Util.scannMinor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(3, 0), "w=h 45 degree line over diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(0, 3), "w=h 45 degree line over diagonal faild second point");
        
        
        pixels = new int[][]{
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,0,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,0,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 1.0;
        context = new Context(pixels, 1, 2);
        result = Util.scannMinor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(8, 3), "w=h 45 degree line under diagonal faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(5, 6), "w=h 45 degree line under diagonal faild second point"); 
        
        
        pixels = new int[][]{
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,0,0,0,2,2,2},
            {2,2,2,0,0,0,2,2,2},
            {2,2,2,0,0,0,2,2,2},
            {2,2,2,0,0,2,2,2,2},
            {2,2,2,0,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = 3.75;
        context = new Context(pixels, 1, 2);
        result = Util.scannMinor(offset, width, height, scanStep, context, slope);

        assertEquals(result.get(0).p1, new Point2D.Double(7.2, 3.0), "w=h 12 degree line faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(6.466666666666667, 5.75), "w=h 12 degree line faild second point"); 
        
        
        pixels = new int[][]{
            {2,2,2,2,2,0,0,0,2},
            {2,2,2,2,2,0,0,2,2},
            {2,2,2,2,2,0,0,2,2},
            {2,2,2,2,0,0,2,2,2},
            {2,2,2,0,0,0,2,2,2},
            
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        slope = Util.slope(width, height, 31);
        context = new Context(pixels, 1, 2);
        result = Util.scannMinor(offset, width, height, scanStep, context, slope);
        
        assertEquals(result.size(), 3);
        assertEquals(result.get(0).p1, new Point2D.Double(1.3103448275862064, 5.3500000000000005), "w<h 12 degree line faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(-0.517241379310345, 8.0), "w<h 12 degree line faild second point"); 
        
        
        pixels = new int[][]{
            {2,2,2,2,2,0},
            {2,2,2,2,0,0},
            {2,2,2,0,0,0},
            {2,2,0,0,2,2},
            {2,0,0,2,2,0},
            {2,2,2,2,2,0},
            {2,2,2,2,2,0},
            {2,2,2,2,2,0},
            
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 2;
        slope = Util.slope(width, height, 44);
        context = new Context(pixels, 1, 2);
        result = Util.scannMinor(offset, width, height, scanStep, context, slope);
        
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).p1, new Point2D.Double(4.0, 1.02), "w>h 12 degree line faild first point");
        assertEquals(result.get(0).p2, new Point2D.Double(1.0196078431372544, 4.0600000000000005), "w>h 12 degree line faild second point"); 
    }
    
    @Test
    public void testSlope() {
        System.out.println("test: slope()");
        
        assertEquals(Util.slope(9, 9, 45), 1.0, "test 45 degree slope");
        assertEquals(Util.slope(23, 39, 42), 1.07, "test 42 degree slope");
        assertEquals(Util.slope(4329, 419, 41), 1.1, "test 41 degree slope");
        assertEquals(Util.slope(9, 94, 35), 1.29, "test 35 degree slope");
        assertEquals(Util.slope(39, 39, 25), 1.8, "test 25 degree slope");
        assertEquals(Util.slope(93, 93, 11), 4.09, "test 11 degree slope");
        assertEquals(Util.slope(931, 193, 5), 9.0, "test 5 degree slope");
        assertEquals(Util.slope(33, 21, 2), 22.5, "test 1 degree slope");
    }
    
    
    @Test
    public void testScannVertically() {
        System.out.println("test: scannVertically()");
        
        int[][] pixels = {
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,0,0,0,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        int offset = 0;
        int width = pixels.length;
        int height = pixels[0].length;
        int scanStep = 1;
        Context context = new Context(pixels, 1, 2);
        List<Line> result = Util.scannVertically(offset, width, height, scanStep, context);

        assertEquals(result.get(0).p1, new Point2D.Double(6, 2), "w=h  first point");
        assertEquals(result.get(0).p2, new Point2D.Double(6, 5), "w=h  second point");
        
        
        pixels = new int[][]{
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,0,0,0,0,0,2},
            {2,2,2,2,2,0,0,0,0},
            {2,2,2,0,0,0,2,2,2},
            {2,2,0,0,0,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        context = new Context(pixels, 1, 2);
        result = Util.scannVertically(offset, width, height, scanStep, context);

        assertEquals(result.size(), 3);
        
    }
    
    
    @Test
    public void testScannHorizontally() {
        System.out.println("test: scannHorizontally()");
        
        int[][] pixels = {
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        int offset = 0;
        int width = pixels.length;
        int height = pixels[0].length;
        int scanStep = 1;
        Context context = new Context(pixels, 1, 2);
        List<Line> result = Util.scannHorizontally(offset, width, height, scanStep, context);
        
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).p1, new Point2D.Double(2, 4), "w=h  first point");
        assertEquals(result.get(0).p2, new Point2D.Double(8, 4), "w=h  second point");
        
        
        pixels = new int[][]{
            {2,2,2,2,0,2,2,2,2},
            {2,2,2,2,0,2,0,2,2},
            {2,2,0,2,0,2,0,0,2},
            {2,2,0,2,0,0,0,0,2},
            {2,2,0,2,2,0,2,0,2},
            {2,2,0,2,2,0,0,2,2},
            {2,2,2,2,2,2,0,2,2},
            {2,2,2,2,2,2,0,2,2},
            {2,2,2,2,2,2,2,2,2},
        };
 
        offset = 0;
        width = pixels.length;
        height = pixels[0].length;
        scanStep = 1;
        context = new Context(pixels, 1, 2);
        result = Util.scannHorizontally(offset, width, height, scanStep, context);

        assertEquals(result.size(), 5);
    }
}
