/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.test.core;

import java.awt.geom.Point2D;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author maryan
 */
public class LineNGTest {

    public LineNGTest() {
    }

    /**
     * Test of GetDiagonalsIntersection method, of class Line.
     */
    @Test
    public void testGetDiagonalsIntersection() {
        System.out.println("getLinesIntersection");

        Line line1 = new Line(new Point2D.Double(1, 0), new Point2D.Double(1, 3));
        Line line2 = new Line(new Point2D.Double(0, 2), new Point2D.Double(4, 2));
        assertEquals(line1.getIntersection(line2), new Point2D.Double(1, 2), "horizontal and vertical - intersect");
        assertEquals(line2.getIntersection(line1), new Point2D.Double(1, 2), "horizontal and vertical - intersect");

        line1 = new Line(new Point2D.Double(2, 2), new Point2D.Double(2, 7));
        line2 = new Line(new Point2D.Double(8, 5), new Point2D.Double(8, 16));
        assertEquals(line1.getIntersection(line2), null, "vertical and vertical - no intersect");
        assertEquals(line2.getIntersection(line1), null, "vertical and vertical - no intersect");

        line1 = new Line(new Point2D.Double(4, 2), new Point2D.Double(7, 2));
        line2 = new Line(new Point2D.Double(3, 8), new Point2D.Double(7, 8));
        assertEquals(line1.getIntersection(line2), null, "horizontal and horizontal - no intersect");
        assertEquals(line2.getIntersection(line1), null, "horizontal and horizontal - no intersect");

        line1 = new Line(new Point2D.Double(2, 0), new Point2D.Double(2, 8));
        line2 = new Line(new Point2D.Double(1, 0), new Point2D.Double(2, 3));
        assertEquals(line1.getIntersection(line2), new Point2D.Double(2, 3), "vertical and major - intersect");
        assertEquals(line2.getIntersection(line1), new Point2D.Double(2, 3), "vertical and major - intersect");

        line1 = new Line(new Point2D.Double(2, 2), new Point2D.Double(2, 5));
        line2 = new Line(new Point2D.Double(3, 3), new Point2D.Double(6, 6));
        assertEquals(line1.getIntersection(line2), null, "vertical and major - no intersect");
        assertEquals(line2.getIntersection(line1), null, "vertical and major - no intersect");

        line1 = new Line(new Point2D.Double(2, 2), new Point2D.Double(2, 7));
        line2 = new Line(new Point2D.Double(-3, 3), new Point2D.Double(5, 10));
        assertEquals(line1.getIntersection(line2), null, "major line under vertical - no intersect");
        assertEquals(line2.getIntersection(line1), null, "major line under vertical - no intersect");

        line1 = new Line(new Point2D.Double(5, 7), new Point2D.Double(5, 15));
        line2 = new Line(new Point2D.Double(9, 1), new Point2D.Double(1, 5));
        assertEquals(line1.getIntersection(line2), null, "minor line over vertical - no intersect");
        
        assertEquals(line2.getIntersection(line1), null, "minor line over vertical - no intersect");
        
        
        line1 = new Line(new Point2D.Double(3,3), new Point2D.Double(17,3));
        line2 = new Line(new Point2D.Double(1,1), new Point2D.Double(5,5));
        assertEquals(line1.getIntersection(line2), new Point2D.Double(3,3), "horizontal and major lines - intersect");
        
        assertEquals(line2.getIntersection(line1), new Point2D.Double(3,3), "horizontal and major lines - intersect");
        
        
        line1 = new Line(new Point2D.Double(3,3), new Point2D.Double(17,3));
        line2 = new Line(new Point2D.Double(12,0), new Point2D.Double(3,51));
        assertEquals(line1.getIntersection(line2), new Point2D.Double(11.470588235294118, 3), "horizontal and minor - intersect");
        
        assertEquals(line2.getIntersection(line1), new Point2D.Double(11.470588235294118, 3), "horizontal and minor - intersect");
        
        line1 = new Line(new Point2D.Double(7,4), new Point2D.Double(17,4));
        line2 = new Line(new Point2D.Double(13,5), new Point2D.Double(2,14));
        assertEquals(line1.getIntersection(line2), null, "horizontal and minor - no intersect");
        
        assertEquals(line2.getIntersection(line1), null, "horizontal and minor - no intersect");
        
        line1 = new Line(new Point2D.Double(2,7), new Point2D.Double(7,7));
        line2 = new Line(new Point2D.Double(2,3), new Point2D.Double(8,6));
        assertEquals(line1.getIntersection(line2), null, "horizontal and major - no intersect");
        
        assertEquals(line2.getIntersection(line1), null, "horizontal and major - nor intersect");
        
        
        line1 = new Line(new Point2D.Double(0,0), new Point2D.Double(4,4));
        line2 = new Line(new Point2D.Double(4,2), new Point2D.Double(7,5));
        assertEquals(line1.getIntersection(line2), null, "major and major - no intersect");
        
        assertEquals(line2.getIntersection(line1), null, "major and major - no intersect");
        
        
        line1 = new Line(new Point2D.Double(1,4), new Point2D.Double(3,6));
        line2 = new Line(new Point2D.Double(3,4), new Point2D.Double(1,6));
        assertEquals(line1.getIntersection(line2), new Point2D.Double(2,5), "major and minor - intersect");
        
        assertEquals(line2.getIntersection(line1), new Point2D.Double(2,5), "major and minor - intersect");
        
        
        line1 = new Line(new Point2D.Double(12,14), new Point2D.Double(31,62));
        line2 = new Line(new Point2D.Double(29,4), new Point2D.Double(-6,34));
        assertEquals(line1.getIntersection(line2), new Point2D.Double(13.351111111111111,17.41333333333333), "major and minor - intersect");
        
        assertEquals(line2.getIntersection(line1), new Point2D.Double(13.351111111111111,17.41333333333333), "major and minor - intersect");
        
        
        line1 = new Line(new Point2D.Double(12,14), new Point2D.Double(31,62));
        line2 = new Line(new Point2D.Double(29,4), new Point2D.Double(-6,34));
        assertEquals(line1.getIntersection(line2), new Point2D.Double(13.351111111111111, 17.41333333333333), "major and minor - intersect");
        
        assertEquals(line2.getIntersection(line1), new Point2D.Double(13.351111111111111, 17.41333333333333), "major and minor - intersect");
        
        
        line1 = new Line(new Point2D.Double(4,4), new Point2D.Double(8,8));
        line2 = new Line(new Point2D.Double(12,4), new Point2D.Double(7,7));
        assertEquals(line1.getIntersection(line2), null, "major and minor - no intersect");
        
        assertEquals(line2.getIntersection(line1), null, "major and minor - no intersect");
        
        
        line1 = new Line(new Point2D.Double(66,34), new Point2D.Double(122,54));
        line2 = new Line(new Point2D.Double(99,14), new Point2D.Double(39,25));
        assertEquals(line1.getIntersection(line2), null, "major and minor - no intersect");
        
        assertEquals(line2.getIntersection(line1), null, "major and minor - no intersect");
        
        
        line1 = new Line(new Point2D.Double(12,1), new Point2D.Double(2,12));
        line2 = new Line(new Point2D.Double(19,5), new Point2D.Double(8,17));
        assertEquals(line1.getIntersection(line2), null, "minor and minor - no intersect");
        
        assertEquals(line2.getIntersection(line1), null, "minor and minor - no intersect");
        
        
        line1 = new Line(new Point2D.Double(0,0), new Point2D.Double(5,3));
        line2 = new Line(new Point2D.Double(2,0), new Point2D.Double(6,5));
        assertEquals(line1.getIntersection(line2), new Point2D.Double(3.846153846153846, 2.3076923076923075), "major and major - intersect");
        
        assertEquals(line2.getIntersection(line1), new Point2D.Double(3.846153846153846, 2.3076923076923075), "major and major - intersect");
        
        
        line1 = new Line(new Point2D.Double(8,0), new Point2D.Double(2,6));
        line2 = new Line(new Point2D.Double(9,2), new Point2D.Double(1,5));
        assertEquals(line1.getIntersection(line2), new Point2D.Double(4.2, 3.8), "minor and minor - intersect");
        
        assertEquals(line2.getIntersection(line1), new Point2D.Double(4.2, 3.8), "minor and minor - intersect");

    }

    @Test
    public void testMinorMajor() {
        System.out.println("testIsMinorIsMajorIsVerticalIsHorizontal");
        Line line1 = new Line(new Point2D.Double(4, 1), new Point2D.Double(7, 4));
        Line line2 = new Line(new Point2D.Double(6, 1), new Point2D.Double(3, 4));
        
        assertTrue(line1.isMajor());
        assertTrue(line2.isMinor());
        
        
        line1 = new Line(new Point2D.Double(2, 5), new Point2D.Double(2, 14));
        line2 = new Line(new Point2D.Double(6, 1), new Point2D.Double(13, 1));
        
        assertTrue(line1.isVertical());
        assertTrue(line2.isHorizontal());

    }

}
