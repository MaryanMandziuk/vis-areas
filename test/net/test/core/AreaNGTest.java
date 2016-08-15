/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.test.core;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author maryan
 */
public class AreaNGTest {
    
    public AreaNGTest() {
    }



    /**
     * Test of getSize method, of class Area.
     */
    @Test
    public void testSort() {
        System.out.println("test: sort()");
        Area area = new Area();
        Line line1 = new Line(new Point2D.Double(4, 1), new Point2D.Double(4, 4));
        Line line2 = new Line(new Point2D.Double(4, 5), new Point2D.Double(4, 7));
        Line line3 = new Line(new Point2D.Double(5, 6), new Point2D.Double(5, 8));
        Line line4 = new Line(new Point2D.Double(5, 10), new Point2D.Double(5, 13));
        area.add(line1);
        area.add(line2);
        area.add(line3);
        area.add(line4);
        area.sort();
        
        assertEquals(area.getSize(), 2);
        List<Line> lines = area.getLines();
        assertEquals(lines.get(0).p1, new Point.Double(4, 1));
        assertEquals(lines.get(0).p2, new Point.Double(4, 7));
        assertEquals(lines.get(1).p1, new Point.Double(5, 6));
        assertEquals(lines.get(1).p2, new Point.Double(5, 13));
    }
    
    @Test
    public void testAngleSort() {
        System.out.println("test: angleSort()");
        Area area = new Area();
        Line line1 = new Line(new Point2D.Double(0, 0), new Point2D.Double(3, 3));
        Line line2 = new Line(new Point2D.Double(5, 5), new Point2D.Double(9, 9));
        Line line3 = new Line(new Point2D.Double(2, 1), new Point2D.Double(5, 4));
        Line line4 = new Line(new Point2D.Double(7, 8), new Point2D.Double(8, 9));
        area.add(line1);
        area.add(line2);
        area.add(line3);
        area.add(line4);
        area.sort();
        
        assertEquals(area.getSize(), 3);
        List<Line> lines = area.getLines();
        assertEquals(lines.get(0).p1, new Point.Double(2, 1));
        assertEquals(lines.get(0).p2, new Point.Double(5, 4));
        assertEquals(lines.get(1).p1, new Point.Double(0, 0));
        assertEquals(lines.get(1).p2, new Point.Double(9, 9));
        assertEquals(lines.get(2).p1, new Point.Double(7, 8));
        assertEquals(lines.get(2).p2, new Point.Double(8, 9));
    }
    
}
