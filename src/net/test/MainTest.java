/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import net.test.core.Area;
import net.test.core.Line;
import net.test.strategy.DefaultScanStrategy;

/**
 *
 * @author maryan
 */
public class MainTest {
    private int[][] pixels;
        private int[][] pixels2;
        private int width;
        private int height;
        private final int lineThreshold = 2;
        private final int colorThreshold = 80;  
        public int scanStep;
        public int degree;
        public MainTest(File original, File adapted, int id, int scanStep, int degree) {
            this.scanStep = scanStep;
            this.degree = degree;
            try {
                BufferedImage image = ImageIO.read(adapted);
                BufferedImage image2 = ImageIO.read(original);
                
                //BufferedImage image2 = null;
                width = image.getWidth();
                height = image.getHeight();

                pixels = new int[width][height];
                pixels2 = new int[width][height];
                
                for (int i = 0; i < width; i++) {
                    for (int j = 0; j < height; j++) {
                        int rgb = image.getRGB(i, j);
                        int r = (rgb >> 16) & 0xFF;
                        int g = (rgb >> 8) & 0xFF;
                        int b = (rgb & 0xFF);
                        
                        pixels[i][j] = rgb;
                        
                        if(null != image2) {
                            pixels2[i][j] = image2.getRGB(i,j);
                        }
                        //0.21 R + 0.72 G + 0.07 B
                        //int gray = ((int) (0.21 * r + 0.72 * g + 0.07 * b) & 0xFF);
                        //pixels[i][j] = 0xFF000000 | gray << 16 | gray << 8 | gray;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        public void paint(File output, int id) throws IOException {

            final int k = 3;
//            Graphics2D g = (Graphics2D) graphics;
            BufferedImage bi = new BufferedImage(k*width, k*height, BufferedImage.TYPE_INT_ARGB); 
            BufferedImage bii = new BufferedImage(k*width, k*height, BufferedImage.TYPE_INT_ARGB); 
            Graphics2D g = bi.createGraphics();
            Graphics2D gg = bii.createGraphics();

            RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            g.setRenderingHints(qualityHints);

            // zoom functionality
            
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int value = pixels[i][j];
                    g.setColor(new Color(value));
                    gg.setColor(new Color(value));
                    if (null != pixels2) {
                        int value2 = pixels2[i][j];
                        g.setColor(new Color(value2));
                        gg.setColor(new Color(value2));
                        g.fillRect(i * k, j * k, k, k);
                        gg.fillRect(i * k, j * k, k, k);
                    }
                }
            }
            
            Context context = new Context(pixels, colorThreshold, lineThreshold);
            context.setScanStep(scanStep);
            DefaultScanStrategy strategy = new DefaultScanStrategy(pixels, width, height);
            ImageScanner scanner = new ImageScanner(strategy, pixels, width, height);
            ScanResult results = scanner.angleScanImage(context, degree);
                   

            
            
            
            gg.setColor(Color.RED);
            
            results.getVerticalLines().stream().forEach((l) -> {
                gg.draw(new Line2D.Double(k * l.p1.x , k * l.p1.y, k * l.p2.x , k * l.p2.y));
            });            
            
            gg.setColor(Color.ORANGE);
            
            results.getHorizontalLines().stream().forEach((l) -> {
                gg.draw(new Line2D.Double(k * l.p1.x, k * l.p1.y, k * l.p2.x, k * l.p2.y));
            });                        

                       
            
            g.setStroke(new BasicStroke(4));
            
            final int minAreaSize = 4*(3-scanStep);
            for (Area a : results.getAreas()) {
                if (a.getSize() > minAreaSize) {
                    Line p = null;
                    List<Line> areaLines = a.getLines();
                    if(areaLines.size() > 15*(3-scanStep)) {
                        g.setColor(Color.GREEN);
                    }else{
                        g.setColor(Color.MAGENTA);                        
                    }
                    for (int i = 0; i < areaLines.size(); i++) {
                        Line l = areaLines.get(i);
                        
                        if (i == 0 || i == areaLines.size() - 1) {
                            g.draw(new Line2D.Double(k * l.p1.x, k * l.p1.y, k * l.p2.x, k * l.p2.y));
                        }
                        
                        if(null != p) {
                            g.draw(new Line2D.Double(k*p.p1.x, k*p.p1.y, k*l.p1.x, k*l.p1.y));
                            g.draw(new Line2D.Double(k*p.p2.x, k*p.p2.y, k*l.p2.x, k*l.p2.y));                           
                        }
                        p = l;
                    }
                }
            }   
                 
           
        ImageIO.write(bi,"png", new File(output + File.separator + "test" + String.format("%02d", id) + "r.png"));
        ImageIO.write(bii,"png", new File(output + File.separator + "test" + String.format("%02d", id) + "p.png"));
            
    }
        
    public static void main(String[] args) throws IOException {
//        File im_adapted = new File("images/Files/test06a.png");
//        File im_original = new File("images/Files/test06a.png");
//        MainTest ob = new MainTest(im_original, im_adapted, 6, 4, 35);
//        ob.paint(new File("testFolder"), 0);
    }
}