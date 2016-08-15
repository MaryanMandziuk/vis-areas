/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.test;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import net.test.core.Area;
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
        assertEquals(result.get(0).p2, new Point(7, 7), "w=h 45 degree diagonal faild second point");
        
        
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
        assertEquals(result.get(0).p2, new Point(4, 8), "w=h 45 degree line over diagonal faild second point");
        
        
        
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
        assertEquals(result.get(0).p2, new Point(2, 8), "w=h 45 degree line over diagonal faild second point"); 
        
        
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
        assertEquals(result.get(0).p2, new Point(8, 6), "w=h 45 degree line under diagonal faild second point"); 
        
        
        
        
        
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

        assertEquals(result.get(0).p1, new Point2D.Double(9, 0), "w=h 45 degree diagonal faild first point");
        assertEquals(result.get(0).p2, new Point(2, 7), "w=h 45 degree diagonal faild second point");

    }

}
