/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 *
 * @author maryan
 */
public class MainTestNGTest {
    private File adapted;
    private File original;
    private int id;
    private int scanStep;
    private int degree;
    private boolean anglePaint;
    public MainTestNGTest() {
        
    }
    public MainTestNGTest(File adapted, File original, int id, int scanStep, int degree, boolean anglePaint) {
        if(adapted != null && original != null) {
            this.adapted = adapted;
            this.original = original;
        } else if (adapted == null) {
            this.adapted = original;
            this.original = original;
        } else if (original == null) {
            this.adapted = adapted;
            this.original = adapted;
        }
        this.id = id;
        this.scanStep = scanStep;
        this.degree = degree;
        this.anglePaint = anglePaint;
    }

    

    /**
     * Test of main method, of class MainForTest.
     */
    @Test
    public void testMain() throws IOException {

        MainTest ob = new MainTest(this.original, this.adapted, this.id, this.scanStep, this.degree, this.anglePaint);
        String s = "scan" + this.scanStep + "degree" + this.degree;
        if (!this.anglePaint) {
            s = "scan" + this.scanStep + "HorizontalVertical";
        }
        File testFolder = new File("testFolder");
        testFolder.mkdir();
        File newTestFolder = new File(testFolder + File.separator + s);
        newTestFolder.mkdirs();
        ob.paint(newTestFolder,id);
        
       
        BufferedImage im1 = ImageIO.read(this.adapted);
        BufferedImage im2 = ImageIO.read(this.original);
        ImageIO.write(im1,"png", new File(newTestFolder + File.separator + "test" + String.format("%02d", this.id) + "a.png"));
        ImageIO.write(im2,"png", new File(newTestFolder + File.separator + "test" + String.format("%02d", this.id) + "o.png"));
        
    }
    
    @Factory
    public Object[] factoryMethod() {
        final int numberOfImages = 20 ;
        final int numberSteps = 3;
        Object[] result = new Object[numberSteps*numberOfImages];

            for(int step = 1; step < numberSteps + 1; step ++ ) {
                int deg = 1+(int)(Math.random()*45);
                int d = 0;
                    if (step > 1) {
                        d = (step - 1) * numberOfImages;
                    }
                for (int i = 0; i < numberOfImages; i++) {
                    File im_adapted = new File("images/Files/test" + String.format("%02d", i + 1) + "a.png");
                    File im_original = new File("images/Files/test" + String.format("%02d", i + 1) + "o.png");
                    
                    if (!im_adapted.exists() ) {
                        System.err.println("There is no more test images" + "  " + i);
                        break;
                    }
                    if (!im_original.exists()) {
                        if (step == numberSteps) {
                           result[i + d] = new MainTestNGTest(im_adapted, null, i + 1, step, deg, true); 
                        } else {
                            result[i + d] = new MainTestNGTest(im_adapted, null, i + 1, step, deg, false);
                        }
                    } else {
                        if (step == numberSteps) {
                           result[i + d] = new MainTestNGTest(im_adapted, null, i + 1, step, deg, true); 
                        } else {
                            result[i + d] = new MainTestNGTest(im_adapted, im_original, i + 1, step, deg, false);
                        }
                    }
                    
                }
               
            }

        return result;
    }
    
}

