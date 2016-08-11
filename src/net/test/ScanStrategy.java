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
public interface ScanStrategy {
    
    /**
     * 
     * @param context
     * @return 
     */
    ScanResult scanImage(Context context);
    ScanResult angleScanImage(Context context, int degree);
}
