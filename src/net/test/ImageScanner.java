/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test;

/**
 *
 * @author renat
 */
public class ImageScanner {

    private final int[][] pixels;
    private final int width;
    private final int height;

    //private final Context context;    
    private final ScanStrategy strategy;
    
    /**
     * 
     * @param strategy
     * @param pixels
     * @param width
     * @param height 
     */
    public ImageScanner(ScanStrategy strategy, int[][] pixels, int width, int height) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        
        this.strategy = strategy;        
    }
    
    public ScanResult scanImage(Context context) {
        return strategy.scanImage(context);
    }    

    public ScanResult angleScanImage(Context context) {
        return strategy.angleScanImage(context);
    }
}
