/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package net.test;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author renat
 */
public class Context {

    private final int[][] pixels;
    private final int colorThreshold;
    private final int lineThreshold;
    private final int timeout = 100;
    private final TimeUnit timeoutUnit = TimeUnit.MILLISECONDS;
    
    private int scanStep = 1;    
    
    /**
     * 
     * @param pixels
     * @param colorThreshold
     * @param lineThreshold 
     */
    public Context(int[][] pixels, int colorThreshold, int lineThreshold) {
        this.pixels = pixels;
        this.colorThreshold = colorThreshold;
        this.lineThreshold = lineThreshold;
    }

    public int getScanStep() {
        return scanStep;
    }

    public void setScanStep(int scanStep) {
        this.scanStep = scanStep;
    }    
    
    /**
     * 
     * @return 
     */
    public int[][] getPixels() {
        return pixels;
    }

    /**
     * 
     * @return 
     */
    public int getColorThreshold() {
        return colorThreshold;
    }

    /**
     * 
     * @return 
     */
    public int getLineThreshold() {
        return lineThreshold;
    }

    public int getTimeout() {
        return timeout;
    }

    public TimeUnit getTimeoutUnit() {
        return timeoutUnit;
    }
    
    
}
